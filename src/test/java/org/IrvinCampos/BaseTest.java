package org.IrvinCampos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.IrvinCampos.PageObject.Android.FormPage;
import org.IrvinCampos.PageObject.Android.utils.AppiumUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class BaseTest{
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;



    @BeforeClass
    public void configureAppium() throws MalformedURLException {
        // Start Appium Server
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:/Users/Irvin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))  // Fixed slashes
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        // Define UiAutomator2 Options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("IrvinEmulator"); // Android emulator
        options.setChromedriverExecutable("C:/Users/Irvin/Desktop/Selenium-Server/ChromeDriver.exe");
        options.setApp("C:/Users/Irvin/Desktop/Appium/src/test/java/org/IrvinCampos/resources/General-Store.apk");

        // Initialize Android Driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Initialize FormPage Object
        formPage = new FormPage(driver);
    }




    public void LongPressAction(WebElement ele) {
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId",((RemoteWebElement) ele).getId(),
                        "duration",2000));
    }

    public void SCrollToEndAction() {
        // No prior knowledge of where to scroll
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 1.0
            ));
        }while (canScrollMore);
    }

    public void SwipeAction(WebElement element, String direction, double percent) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", percent
        ));
    }

    public void DragAndDropAction(WebElement element, int x, int y) {
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", x,
                "endY", y
        ));
    }

    public Double formattedAmount(String amount) {
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }
    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<>() {});
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
        service.stop();

    }
}