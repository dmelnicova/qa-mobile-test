package com.alfa.gui.android;

import com.alfa.gui.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSuccessPage extends BasePage {

    @FindBy(css = ".android.widget.TextView")
    private WebElement successMessage;

    public LoginSuccessPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isOpened() {
        return successMessage.isDisplayed() && successMessage.getText().equals(LOGIN_SUCCESS_PAGE_TITLE);
    }
}
