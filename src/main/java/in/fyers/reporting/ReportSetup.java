package in.fyers.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;


public class ReportSetup implements ITestListener {

    private static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> testExtent = new ThreadLocal<>();

    public void onStart(ITestContext context) {
        String fileName = ExtentReportManager.getReportNameWithTimeStamp();
        String fullReportPath = System.getProperty("user.dir") + "/reports/" + fileName;
        extentReports = ExtentReportManager.createInstance(fullReportPath, "API Daily Monitoring Automation Report", "API Daily Monitoring");
    }

    public void onFinish(ITestContext context) {
        if(extentReports != null){
            extentReports.flush();
        }
    }

    public void onTestStart(ITestResult result){
        ExtentTest test = extentReports.createTest("Test Name: " + result.getMethod().getMethodName())
                .assignCategory(result.getMethod().getGroups());
        testExtent.set(test);
    }

    public void onTestFailure(ITestResult result){
        ExtentReportManager.logFailDetails(result.getThrowable().getMessage());
        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
        stackTrace = stackTrace.replaceAll(",", "<br>");
        String formattedTrace = "";
        formattedTrace = "<details><summary>Click here to view detail logs....</summary>" + formattedTrace + "</details>";
        ExtentReportManager.logStackTraceDetails(stackTrace);
    }

    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.logPassDetails(result.getName() + " is passed");
    }


}
