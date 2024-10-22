package org.IrvinCampos;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExtentReportDemo {
    ExtentReports extentReports;
    @BeforeTest(enabled = false)
    public void config() {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("Web Automation Results");
        extentSparkReporter.config().setDocumentTitle("Test Results");
         extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester","Irvin Campos");
    }
    @Test(enabled = false)
    public void initialDemo() {
        ExtentTest test = extentReports.createTest("Initial Demo");
        System.setProperty("webdriver.chrome.driver","C://Users//Irvin//Desktop//Selenium-Server//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.youtube.com/");
        System.out.println(driver.getTitle());
        driver.close();
        test.fail("Results do not match");
        extentReports.flush();
    }
}
