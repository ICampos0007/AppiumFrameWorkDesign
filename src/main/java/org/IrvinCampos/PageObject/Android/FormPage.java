package org.IrvinCampos.PageObject.Android;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage {
    AndroidDriver driver;

    public FormPage(AndroidDriver driver) {
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