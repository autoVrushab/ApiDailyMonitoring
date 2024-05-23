package in.fyers.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import in.fyers.utils.DateUtil;
import io.restassured.http.Header;

import java.util.List;

public class ExtentReportManager {

    public static ExtentSparkReporter extentSparkReporter;
    public static ExtentReports extentReports;

    public static ExtentReports createInstance(String fileName, String reportName, String documentTitle){
        extentSparkReporter = new ExtentSparkReporter(fileName);
        extentSparkReporter.config().setReportName(reportName);
        extentSparkReporter.config().setDocumentTitle(documentTitle);
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setEncoding("utf-8");

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        return extentReports;
    }

    public static String getReportNameWithTimeStamp(){
        String dateAndTime = DateUtil.returnDateInSpecificFormat("yyyy_MM_dd_HH_mm_ss");
        return "API_Monitoring" + dateAndTime + ".html";
    }

    public static void logPassDetails(String log){
        ReportSetup.testExtent.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }

    public static void logFailDetails(String log){
        ReportSetup.testExtent.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }

    public static void logInfoDetails(String log){
        ReportSetup.testExtent.get().info(MarkupHelper.createLabel(log, ExtentColor.BLUE));
    }

    public static void logWarningDetails(String log){
        ReportSetup.testExtent.get().pass(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
    }

    public static void logJson(String json){
        ReportSetup.testExtent.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }

    public static void logHeaders(List<Header> headerList){
        String[][] arrHeaders = headerList.stream().map(header -> new String[]{header.getName(), header.getValue()})
                        .toArray(String[][] :: new);
        ReportSetup.testExtent.get().info(MarkupHelper.createTable(arrHeaders));
    }

    public static void logStackTraceDetails(String log){
        ReportSetup.testExtent.get().fail(log);
    }
}
