package org.IrvinCampos.PageObject.Android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.IrvinCampos.PageObject.Android.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AndroidActions {
    AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

//    List<WebElement> productPrices =  driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPrices;

//    String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement displaySum;

//    WebElement termButton = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termButton;

//    String terms = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
    @AndroidFindBy(id = "com.androidsample.generalstore:id/alertTitle")
    private WebElement terms;

//    driver.findElement(By.id("android:id/button1")).click();
    @AndroidFindBy(id = "android:id/button1")
    private WebElement androidButton1;

//    driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;

//    driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedButton;

    public double getProductSum() {
        int productCount = productPrices.size();
        double totalSum =0;
        for (int i=0; i<productCount; i++) {
            String amountString = productPrices.get(i).getText();
            Double price = formattedAmount(amountString);
            totalSum = totalSum + price;
        }
        return totalSum;
    }

    public double getDisplaySum() {
        return getFormattedAmount(displaySum.getText());
    }
    public Double getFormattedAmount(String amount) {
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public void termButtonLongPress() {
        LongPressAction(termButton);
    }

    public String getTerms(){
        return terms.getText();
    }

    public void submitOrder() throws InterruptedException {
        androidButton1.click();
        checkBox.click();
        proceedButton.click();
        Thread.sleep(2000);
    }
}
