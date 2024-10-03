package org.IrvinCampos.PageObject.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.IrvinCampos.PageObject.Android.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AndroidActions {
    AndroidDriver driver;
//    driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCart;

//    driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartButton;

    public ProductCatalogue(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void addItemToCartByIndex(int index) {
        addToCart.get(index).click();
    }

    public CartPage clickCartButton() throws InterruptedException {
        cartButton.click();
        Thread.sleep(2000);
        return new CartPage(driver);
    }
}
