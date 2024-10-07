
package org.IrvinCampos;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.IrvinCampos.PageObject.Android.CartPage;
import org.IrvinCampos.PageObject.Android.FormPage;
import org.IrvinCampos.PageObject.Android.ProductCatalogue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class ECommerceFourHybridTest extends BaseTest{
    @Test
    public void FillFormTest() throws InterruptedException {
        formPage.setNameField("Miya");
        formPage.setGender("female");
        formPage.setCountrySelect("Colombia");
        ProductCatalogue productCatalogue = formPage.submitForm();
        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.addItemToCartByIndex(0);
        CartPage cartPage = productCatalogue.clickCartButton();

        WebElement title = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));

        cartPage.waitForElementToAppear(title);
        double totalSum = cartPage.getProductSum();
        double displayFormattedSum = cartPage.getDisplaySum();
        Assert.assertEquals(totalSum,displayFormattedSum);
        cartPage.termButtonLongPress();
        String terms = cartPage.getTerms();
        Assert.assertEquals(terms, "Terms Of Conditions");
        cartPage.submitOrder();

    }
}
