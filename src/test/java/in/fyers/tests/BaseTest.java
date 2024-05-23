package in.fyers.tests;

import in.fyers.pojo.response.login.LoginResponse;
import in.fyers.pojo.response.token.SendOtp;
import in.fyers.pojo.response.token.Token;
import in.fyers.pojo.response.token.VerifyOtp;
import in.fyers.pojo.response.token.VerifyPin;
import in.fyers.tests.monitoring.constants.Headers;
import in.fyers.tests.monitoring.constants.Uri;
import in.fyers.tests.monitoring.payloads.PayLoad;
import in.fyers.utils.ApiUtils;
import in.fyers.utils.UrlUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    public static String baseUri;
    private static String accessToken;

    public static String getBaseUri(){
        baseUri = "https://api-t1.fyers.in";
        return baseUri;
    }


    public synchronized static String getAccessToken() {
        if(accessToken==null){
            try{
                accessToken = generateAccessTokenUsingLoginApi();
            }
            catch (Exception e){
                System.out.println("Caught Exception");
            }
        }
        return accessToken;
    }

    public static String generateAccessTokenUsingLoginApi() throws MalformedURLException, UnsupportedEncodingException {
        String sendOtpUri = "https://api-t2.fyers.in/vagator/v2/send_login_otp";
        Map<String, Object> rawBody = PayLoad.generateSendOtpData();
        Map<String,String> headers = new HashMap<>();
        Response response = ApiUtils.performPOST(sendOtpUri, rawBody, headers);
        SendOtp sendOtp = response.getBody().as(SendOtp.class);

        String requestKeyToSendOtp = sendOtp.getRequest_key();

        String verifyOtpUri = "https://api-t2.fyers.in/vagator/v2/verify_otp";
        Map<String, Object> rawBody2 = PayLoad.generateVerifyOtpData(requestKeyToSendOtp);
        Response response2 = ApiUtils.performPOST(verifyOtpUri, rawBody2, headers);
        VerifyOtp verifyOtp = response2.getBody().as(VerifyOtp.class);

        String requestKeyToVerifyOtp = verifyOtp.getRequest_key();

        String verifyPinUri = "https://api-t2.fyers.in/vagator/v2/verify_pin";
        Map<String, Object> rawBody3 = PayLoad.generateVerifyPinData(requestKeyToVerifyOtp);
        Response response3 = ApiUtils.performPOST(verifyPinUri, rawBody3, headers);
        VerifyPin verifyPin = response3.getBody().as(VerifyPin.class);

        String accessToken = verifyPin.getData().getAccess_token();

        String verifyTokenUri = "https://api.fyers.in/api/v2/token";
        Map<String, Object> tokenBody = PayLoad.generateTokenData();
        String generateBearerToken = "Bearer " + accessToken;
        Map<String, String> header = new HashMap<>();
        header.put(Headers.AccessToken.getValue(), generateBearerToken);

        Response response4 = ApiUtils.performPOST(verifyTokenUri, tokenBody, header);
        Token tokenData = response4.getBody().as(Token.class);

        String tokenUriFromResponse = tokenData.getUrl();

        String authCode = UrlUtil.returnToken(tokenUriFromResponse);

        String appId = "E30OQX14VP-100:";
        String baseUri = getBaseUri() + Uri.Login.getValue();
        Map<String, Object> body;
        body = PayLoad.generateLoginPayLoad(authCode);
        Map<String, String> headers2 = new HashMap<>();
        headers.put("Content-Type", "application/json");
        Response response5 = ApiUtils.performPOST(baseUri, body, headers2);
        LoginResponse loginResponse = response5.getBody().as(LoginResponse.class);
        Assert.assertEquals(response5.statusCode(), 200);
        Assert.assertEquals(loginResponse.getS(), "ok");
        Assert.assertEquals(loginResponse.getMessage(), "");
        Assert.assertEquals(loginResponse.getCode(), 200);
        Assert.assertNotNull(loginResponse.getAccess_token());
        Assert.assertNotNull(loginResponse.getRefresh_token());
        accessToken = appId + loginResponse.getAccess_token();
        return accessToken;

    }
}
