package in.fyers.utils;

import com.aventstack.extentreports.reporter.ExtentReporter;
import in.fyers.reporting.ExtentReportManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.Map;

public class ApiUtils {

    private static RequestSpecification postRequestSpecification(String baseUrl, Map<String, Object> body, Map<String, String> headers){
        return RestAssured.given()
                .baseUri(baseUrl)
                .headers(headers)
                .body(body);
    }

    private static RequestSpecification getRequestSpecification(String baseUrl,  Map<String, String> queryParams, Map<String, String> headers){
        return RestAssured.given()
                .baseUri(baseUrl)
                .headers(headers)
                .queryParams(queryParams);
    }

    private static void printRequestLogInReport(RequestSpecification requestSpecification){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("Endpoint: " + queryableRequestSpecification.getBaseUri());
        ExtentReportManager.logInfoDetails("API Method: " + queryableRequestSpecification.getMethod());
        ExtentReportManager.logInfoDetails("Request Headers: ");
        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Request Body: ");
        ExtentReportManager.logJson(queryableRequestSpecification.getBody());
        ExtentReportManager.logInfoDetails("Headers: " + queryableRequestSpecification.getQueryParams().toString());
    }

    private static void printResponseLogInReport(Response response){
        ExtentReportManager.logInfoDetails("Response Status: " + response.getStatusCode());
        ExtentReportManager.logInfoDetails("Response Headers: ");
        ExtentReportManager.logHeaders(response.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Response Body: ");
        ExtentReportManager.logJson(response.getBody().prettyPrint());
    }

    /**
     * Method to perform POST action
     * @param baseUrl - Base URL to be hit
     * @param body - Request body to be passed
     * @param headers - Headers to be passed
     * @return - returns the API response
     */
    public static Response performPOST(String baseUrl, Map<String, Object> body, Map<String, String> headers){
        RequestSpecification requestSpecification = postRequestSpecification(baseUrl, body, headers);
        Response res = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(res);
        return res;
    }

    /**
     * Method to perform GET action
     * @param baseUrl - Base URL to be hit
     * @param queryParams - Query params to be passed
     * @param headers - Headers to be passed
     * @return - returns the API response
     */
    public static Response performGET(String baseUrl, Map<String, String> queryParams, Map<String, String> headers){
        RequestSpecification requestSpecification = getRequestSpecification(baseUrl, queryParams, headers);
        Response res = requestSpecification.get();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(res);
        return res;
    }
}
