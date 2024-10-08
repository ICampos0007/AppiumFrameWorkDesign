package org.IrvinCampos;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ECommerceTwoTest extends BaseTest{

    @BeforeMethod
    public void preSetup() {
        //screen to homepage
        Activity activity =  new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
        ((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));

    }
    @Test
    public void FillFormFailTest() throws InterruptedException {
//        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Miya");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Colombia\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Colombia']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage,"Please enter your name");

    }

    @Test
    public void FillFormPassTest() throws InterruptedException {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Miya");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Colombia\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Colombia']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }
}