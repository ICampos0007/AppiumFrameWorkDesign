package org.IrvinCampos;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.Activity;
import org.IrvinCampos.PageObject.Android.CartPage;
import org.IrvinCampos.PageObject.Android.ProductCatalogue;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class ECommerceFourHybridTest extends BaseTest{
    @Test(dataProvider="getData")
    public void FillFormTest(HashMap<String,String> input) throws InterruptedException {
        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountrySelect(input.get("country"));
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

    @DataProvider
    public Object[][] getData() throws IOException {
        String jsonFilePath = System.getProperty("user.dir") +
                "/src/test/java/org/IrvinCampos/TestData/eCommerce.json";
        List<HashMap<String, String>> data = getJsonData(jsonFilePath);
        return new Object[][]{
                {data.get(0)},
                {data.get(1)}
        };
    }
    @BeforeMethod(enabled = false)
    public void preSetup() {
        //screen to homepage
        formPage.setActivity();
    }
}