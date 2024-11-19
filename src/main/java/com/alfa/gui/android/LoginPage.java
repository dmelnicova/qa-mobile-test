package com.alfa.gui.android;

import com.alfa.gui.BasePage;
import com.alfa.utils.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[contains(@resource-id, 'tvTitle')]")
    private WebElement title;

    @FindBy(xpath = "//*[contains(@resource-id, 'etUsername')]")
    private WebElement loginInputField;

    @FindBy(xpath = "//*[contains(@resource-id, 'tilUsername')]//*[contains(@resource-id, 'textinput_placeholder')]")
    private WebElement loginInputPlaceholder;

    @FindBy(xpath = "//*[contains(@resource-id, 'etPassword')]")
    private WebElement passwordInputField;

    @FindBy(xpath = "//*[contains(@resource-id, 'tilPassword')]//*[contains(@resource-id, 'textinput_placeholder')]")
    private WebElement passwordInputPlaceholder;

    @FindBy(css = "[resource-id $= 'text_input_end_icon']")
    private WebElement showPasswordIcon;

    @FindBy(xpath = "//*[contains(@resource-id, 'btnConfirm')]")
    private WebElement loginButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'tvError')]")
    private WebElement errorMessage;

    @FindBy(css = ".android.widget.ProgressBar")
    private WebElement progressBar;

    public LoginPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isOpened() {
        return isOpened(SHORT_TIMEOUT);
    }

    public boolean isOpened(long timeout) {
        waitForElementIsVisible(title, timeout);
        return title.isDisplayed() && title.getText().equals(LOGIN_PAGE_TITLE);
    }

    public boolean isPasswordHidden() {
        return Boolean.parseBoolean(passwordInputField.getAttribute(PASSWORD_ATTRIBUTE));
    }

    public boolean isShowPasswordIconChecked() {
        return Boolean.parseBoolean(showPasswordIcon.getAttribute(CHECKED_ATTRIBUTE));
    }

    public void setLogin(String login) {
        loginInputField.sendKeys(login);
    }

    public void setPassword(String password) {
        passwordInputField.sendKeys(password);
    }

    public String getLoginInputFieldText() {
        return loginInputField.getText();
    }

    public String getLoginPlaceholderText() {
        return getLoginPlaceholderText(SHORT_TIMEOUT);
    }

    public String getLoginPlaceholderText(long timeout) {
        waitForElementIsVisible(loginInputPlaceholder, timeout);
        return loginInputPlaceholder.getText();
    }

    public String getPasswordInputFieldText() {
        return passwordInputField.getText();
    }

    public String getPasswordPlaceholderText() {
        return getPasswordPlaceholderText(SHORT_TIMEOUT);
    }

    public String getPasswordPlaceholderText(long timeout) {
        waitForElementIsVisible(passwordInputPlaceholder, timeout);
        return passwordInputPlaceholder.getText();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public void clickOnLoginInputField() {
        loginInputField.click();
    }

    public void clickOnPasswordInputField() {
        passwordInputField.click();
    }

    public void clickOnShowPasswordIcon() {
        showPasswordIcon.click();
    }

    public LoginSuccessPage clickOnLoginButton() {
        return clickOnLoginButton(SHORT_TIMEOUT);
    }

    public LoginSuccessPage clickOnLoginButton(long timeout) {
        loginButton.click();
        waitForElementIsNotVisible(progressBar, timeout);
        return new LoginSuccessPage(AppiumDriverManager.getDriver());
    }

}
