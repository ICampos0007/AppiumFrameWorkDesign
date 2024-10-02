package org.IrvinCampos.PageObject.Android;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.IrvinCampos.PageObject.Android.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndroidActions {
    AndroidDriver driver;

    public FormPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        //
    }
    //    driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Miya");
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    //    driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleOption;

    //    driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement maleOption;
//    driver.findElement(By.id("android:id/text1")).click();
    @AndroidFindBy(id = "android:id/text1")
    private WebElement countrySelect;

//    driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    public void shopButtonClick() {
        shopButton.click();
    }

    public void setCountrySelect(String countryName) {
        countrySelect.click();
        // Use countryName in the scroll and xpath expressions
        ScrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();

//        driver.findElement(AppiumBy.androidUIAutomator(
//                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + countryName + "\"));"));
//
//        driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
    }

    public void setNameField(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender) {
        if (gender.contains("female")) {
            femaleOption.click();
        }
        else
            maleOption.click();

    }
}