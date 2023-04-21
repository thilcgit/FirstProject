package tester.tests.testcomponents;

import GenerateReports.ExtentReportNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent=ExtentReportNG.getReportObject();
    ExtentTest test;
    ThreadLocal <ExtentTest>threadL=new ThreadLocal <ExtentTest>();//Thread safe
    @Override
    public void onTestStart(ITestResult result) {
        //before starting any test control comes here
        ITestListener.super.onTestStart(result);
        //test report entry is created
        test=extent.createTest(result.getMethod().getMethodName());
        threadL.set(test);//assign one unique thread id(ErrorValidation)->test

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        threadL.get().log(Status.PASS,"Test Passed");
        //test.get().log(Status.PASS,"Test Passed")


    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        //test.get().fail(result.getThrowable())
        threadL.get().fail(result.getThrowable());//error message
        String filePath= null;
        try {
            driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            filePath = String.valueOf(getScreenshot(result.getMethod().getMethodName(),driver));
        } catch ( Exception e) {
            throw new RuntimeException(e);
        }
        //test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName())
        threadL.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extent.flush();
    }
}
