package org.IrvinCampos.TestUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.IrvinCampos.PageObject.Android.FormPage;
import org.IrvinCampos.PageObject.Android.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest extends AppiumUtils {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;

    @BeforeClass(alwaysRun = true)
    public void configureAppium(ITestContext context) throws IOException {
        // Load Properties
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/data.properties");
        properties.load(fileInputStream);

        // Read values from properties
        String ipAddress = properties.getProperty("ipAddress");
        String port = properties.getProperty("port");
        String emulator = properties.getProperty("IrvinEmulator");

        // Start Appium Server
        startAppiumServer(ipAddress, Integer.parseInt(port));

        // Configure UiAutomator2Options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(emulator);
        options.setChromedriverExecutable("C:/Users/Irvin/Desktop/Selenium-Server/ChromeDriver.exe");
        options.setApp(System.getProperty("user.dir") + "/src/test/java/resources/General-Store.apk");

        // Initialize Android Driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Store driver in TestContext for use in Listeners
        context.setAttribute("driver", driver);

        // Initialize FormPage
        formPage = new FormPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}
