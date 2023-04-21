package GenerateReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

    public static ExtentReports getReportObject(){

        String reportPath=System.getProperty("user.dir")+"\\Reports\\index.html";
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter(reportPath);
        sparkReporter.config().setReportName("Thilini Test Results");
        sparkReporter.config().setDocumentTitle("Report Results-1");

        ExtentReports extentReports=new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Tester","Thilini");
        return extentReports;

    }
    }


