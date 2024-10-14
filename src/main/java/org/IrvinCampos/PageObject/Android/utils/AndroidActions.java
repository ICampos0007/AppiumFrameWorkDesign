
package org.IrvinCampos.PageObject.Android.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions extends AppiumUtils{
    AndroidDriver driver;

    public AndroidActions(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void LongPressAction(WebElement ele) {
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId",((RemoteWebElement) ele).getId(),
                        "duration",2000));
    }

    public void ScrollToEndAction() {
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

    public void ScrollToText(String text){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" +text + "\"));"));
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
}
