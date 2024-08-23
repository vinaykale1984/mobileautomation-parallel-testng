package com.qa.listener;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.reports.ExtentReport;
import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParameters;
import com.qa.utils.TestUtils;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.apache.logging.log4j.core.util.FileUtils.getFileExtension;

public class TestListener implements ITestListener {
    TestUtils utils = new TestUtils();
    DriverManager driver = new DriverManager();
    GlobalParameters parameters = new GlobalParameters();

    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
            utils.log().error(sw.toString());
        }

        File file = driver.getDriver().getScreenshotAs(OutputType.FILE);

        byte[] encoded = null;
        try{
            encoded = Base64.encodeBase64(getFileExtension(file).getBytes());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, String> parameters = new HashMap<>();
        parameters = result.getTestContext().getCurrentXmlTest().getAllParameters();

        String imagePath = "ScreenShots" + File.separator + parameters.get("platFormName")
                + "_" + parameters.get("deviceName") + File.separator + result.getTestClass()
                .getRealClass().getSimpleName() + File.separator + result.getName() + ".png";

        String completeImagePath = System.getProperty("user-dir") + File.separator + imagePath;
        try {
            getFileExtension(new File(imagePath));
            Reporter.log("ScreenShot");
            Reporter.log("<a href='" + completeImagePath + "'> <img src='" + completeImagePath + "'height = '400' width = '400'/></a>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        ExtentReport.getTest().log(Status.FAIL, "Test Failed");
        try {
            ExtentReport.getTest().fail("Test Failed",
                    MediaEntityBuilder.createScreenCaptureFromPath(completeImagePath).build());

            ExtentReport.getTest().fail("test Failed",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentReport.startTest(result.getName(), result.getMethod().getDescription()).
                assignCategory(parameters.getPlatformName() + "_" + parameters.getDeviceName())
                .assignAuthor("VinayNarayanrao");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReport.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReport.getTest().log(Status.SKIP, "Test Skipped");
        ExtentReport.getReporter().flush();
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
        ExtentReport.getReporter().flush();
    }
}
