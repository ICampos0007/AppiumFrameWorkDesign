package org.IrvinCampos.PageObject.Android.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public abstract class AppiumUtils {

    public AppiumDriverLocalService service;

    public Double getFormattedAmount(String amount) {
        return Double.parseDouble(amount.substring(1));
    }

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    }

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:/Users/Irvin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .withIPAddress(ipAddress)
                .usingPort(port)
                .build();
        service.start();
        return service;
    }

    public void waitForElementToAppear(WebElement element, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(element, "text", "Cart"));
    }

    public String getScreenShotPath(AppiumDriver driver, String testCaseName) throws IOException {

        File src= ((TakesScreenshot)driver ).getScreenshotAs((OutputType.FILE));
        File destination = new File(System.getProperty("user.dir")+"//reports" +testCaseName+".png");
        String filepath = destination.getAbsolutePath();
        FileUtils.copyFile(src, destination);
        return filepath;
    }
}
