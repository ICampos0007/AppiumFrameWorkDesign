package org.IrvinCampos.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.IrvinCampos.PageObject.Android.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends AppiumUtils implements ITestListener{
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReporterObject();
    AppiumDriver driver;


    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        test= extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        test.log(Status.PASS, "Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());

        try {
            // Retrieve driver from TestContext
            driver = (AppiumDriver) result.getTestContext().getAttribute("driver");
            if (driver != null) {
                String screenshotPath = getScreenShotPath(driver, result.getMethod().getMethodName());
                System.out.println("Screenshot saved at: " + screenshotPath);
                test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
            } else {
                System.err.println("Driver is null, could not capture screenshot.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        extent.flush();
    }
//

}
