package com.alfa.login.android;

import com.alfa.BaseTest;
import com.alfa.gui.android.LoginPage;
import com.alfa.gui.android.LoginSuccessPage;
import com.alfa.utils.AppiumDriverManager;
import com.alfa.utils.PropertiesUtil;
import com.alfa.utils.StringGenerator;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.SecureRandom;

public class LoginTest extends BaseTest {

    @Test(testName = "Verify that the user can login after entering the valid user's login and password")
    @Owner("Diana Melnikova")
    public void verifyUserCanLoginWithValidLoginPasswordTest() {
        LoginPage loginPage = new LoginPage(AppiumDriverManager.getDriver());
        Assert.assertTrue(loginPage.isOpened(), String.format("%s Page should be opened!", PAGE_NAME_LOGIN));
        String login = PropertiesUtil.getTestDataValue("user1.login");
        loginPage.setLogin(login);
        Assert.assertEquals(loginPage.getLoginInputFieldText(), login,
                String.format("%s Login input field text should be as expected!", PAGE_NAME_LOGIN));
        String password = PropertiesUtil.getTestDataValue("user1.password");
        loginPage.setPassword(password);
        Assert.assertEquals(loginPage.getPasswordInputFieldText(), password,
                String.format("%s Password input field text should be as expected!", PAGE_NAME_LOGIN));
        LoginSuccessPage loginSuccessPage = loginPage.clickOnLoginButton();
        Assert.assertTrue(loginSuccessPage.isOpened(), String.format("%s Page should be opened!", PAGE_NAME_LOGIN_SUCCESS));
    }

    @Test(testName = "Verify that there are no placeholders in login input field and login input field has 'Логин' label")
    @Owner("Diana Melnikova")
    public void verifyLoginFieldLabelAndPlaceholderTest() {
        LoginPage loginPage = new LoginPage(AppiumDriverManager.getDriver());
        Assert.assertTrue(loginPage.isOpened(), String.format("%s Page should be opened!", PAGE_NAME_LOGIN));
        Assert.assertEquals(loginPage.getLoginInputFieldText(), LOGIN_LABEL,
                String.format("%s Login label text text should be as expected!", PAGE_NAME_LOGIN));
        loginPage.clickOnLoginInputField();
        Assert.assertEquals(loginPage.getLoginPlaceholderText(), "",
                String.format("%s Login input field should not have any placeholders!", PAGE_NAME_LOGIN));
    }

    @Test(testName = "Verify that there are no placeholders in password input field and login input field has 'Пароль' label")
    @Owner("Diana Melnikova")
    public void verifyPasswordFieldLabelAndPlaceholderTest() {
        LoginPage loginPage = new LoginPage(AppiumDriverManager.getDriver());
        Assert.assertTrue(loginPage.isOpened(), String.format("%s Page should be opened!", PAGE_NAME_LOGIN));
        Assert.assertEquals(loginPage.getPasswordInputFieldText(), PASSWORD_LABEL,
                String.format("%s Login label text text should be as expected!", PAGE_NAME_LOGIN));
        loginPage.clickOnPasswordInputField();
        Assert.assertEquals(loginPage.getPasswordPlaceholderText(), "",
                String.format("%s Login input field should not have any placeholders!", PAGE_NAME_LOGIN));
    }

    @Test(testName = "Verify that user can not enter login with the length more then 50 characters")
    @Owner("Diana Melnikova")
    @Issue("AUTH-001")
    public void verifyUserCannotEnterLoginWithInvalidLengthTest() {
        LoginPage loginPage = new LoginPage(AppiumDriverManager.getDriver());
        Assert.assertTrue(loginPage.isOpened(), String.format("%s Page should be opened!", PAGE_NAME_LOGIN));
        SecureRandom random = new SecureRandom();
        String login = StringGenerator.generateRandomStringByRegex(VALID_PASSWORD_REGEX, random.nextInt(15) + 50);
        String expectedLogin = login.substring(0, 50);
        loginPage.setLogin(login);
        Assert.assertEquals(loginPage.getLoginInputFieldText(), expectedLogin,
                String.format("%s User should be able to enter a login with a maximum length of 50 characters!", PAGE_NAME_LOGIN));
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(), AUTORIZE_ERROR_MESSAGE,
                String.format("%s Error message text should be as expected!", PAGE_NAME_LOGIN));
        LoginSuccessPage loginSuccessPage = new LoginSuccessPage(AppiumDriverManager.getDriver());
        Assert.assertFalse(loginSuccessPage.isOpened(), String.format("%s Page should not be opened!", PAGE_NAME_LOGIN_SUCCESS));
    }

    @Test(testName = "Verify that user can not enter password with the length more then 50 characters")
    @Owner("Diana Melnikova")
    @Issue("AUTH-002")
    public void verifyUserCannotEnterPasswordWithInvalidLengthTest() {
        LoginPage loginPage = new LoginPage(AppiumDriverManager.getDriver());
        Assert.assertTrue(loginPage.isOpened(), String.format("%s Page should be opened!", PAGE_NAME_LOGIN));
        SecureRandom random = new SecureRandom();
        String password = StringGenerator.generateRandomStringByRegex(VALID_PASSWORD_REGEX, random.nextInt(15) + 50);
        loginPage.setPassword(password);
        String expectedPassword = password.substring(0, 50);
        Assert.assertEquals(loginPage.getPasswordInputFieldText(), expectedPassword,
                String.format("%s User should be able to enter a password with a maximum length of 50 characters!", PAGE_NAME_LOGIN));
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(), AUTORIZE_ERROR_MESSAGE,
                String.format("%s Error message text should be as expected!", PAGE_NAME_LOGIN));
        LoginSuccessPage loginSuccessPage = new LoginSuccessPage(AppiumDriverManager.getDriver());
        Assert.assertFalse(loginSuccessPage.isOpened(), String.format("%s Page should not be opened!", PAGE_NAME_LOGIN_SUCCESS));
    }

    @Test(testName = "Verify that the password is hidden by default, user can change password's visibility by clicking on show password icon")
    @Owner("Diana Melnikova")
    public void verifyUserCanShowAndHidePasswordTest() {
        LoginPage loginPage = new LoginPage(AppiumDriverManager.getDriver());
        Assert.assertTrue(loginPage.isOpened(), String.format("%s Page should be opened!", PAGE_NAME_LOGIN));
        String password = StringGenerator.generateRandomStringByRegex(VALID_PASSWORD_REGEX, new SecureRandom().nextInt(51));
        loginPage.setPassword(password);
        Assert.assertTrue(loginPage.isPasswordHidden(),
                String.format("%s Password should be hidden by default!", PAGE_NAME_LOGIN));
        Assert.assertFalse(loginPage.isShowPasswordIconChecked(),
                String.format("%s Show password icon should be unchecked by default!", PAGE_NAME_LOGIN));
        loginPage.clickOnShowPasswordIcon();
        Assert.assertFalse(loginPage.isPasswordHidden(),
                String.format("%s Password should not be hidden after clicking on show password icon!", PAGE_NAME_LOGIN));
        Assert.assertTrue(loginPage.isShowPasswordIconChecked(),
                String.format("%s Show password icon should be unchecked after clicking on show password icon!", PAGE_NAME_LOGIN));
        Assert.assertEquals(loginPage.getPasswordInputFieldText(), password,
                String.format("%s Password should be the same as the user entered!", PAGE_NAME_LOGIN));
        loginPage.clickOnShowPasswordIcon();
        Assert.assertTrue(loginPage.isPasswordHidden(),
                String.format("%s Password should be hidden after clicking on show password icon twice!", PAGE_NAME_LOGIN));
        Assert.assertFalse(loginPage.isShowPasswordIconChecked(),
                String.format("%s Show password icon should be unchecked after clicking on show password icon twice!", PAGE_NAME_LOGIN));
    }

    @Test(testName = "Verify that an unregistered user cannot login with a valid login and password")
    @Owner("Diana Melnikova")
    public void verifyUnregisteredUserCannotLoginWithValidLoginPasswordTest() {
        LoginPage loginPage = new LoginPage(AppiumDriverManager.getDriver());
        Assert.assertTrue(loginPage.isOpened(), String.format("%s Page should be opened!", PAGE_NAME_LOGIN));
        SecureRandom random = new SecureRandom();
        String login = StringGenerator.generateRandomStringByRegex(VALID_LOGIN_REGEX, random.nextInt(51));
        loginPage.setLogin(login);
        Assert.assertEquals(loginPage.getLoginInputFieldText(), login,
                String.format("%s Login should be the same as the user entered!", PAGE_NAME_LOGIN));
        String password = StringGenerator.generateRandomStringByRegex(VALID_PASSWORD_REGEX, random.nextInt(51));
        loginPage.setPassword(password);
        Assert.assertEquals(loginPage.getPasswordInputFieldText(), password,
                String.format("%s Password should be the same as the user entered!", PAGE_NAME_LOGIN));
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(), AUTORIZE_ERROR_MESSAGE,
                String.format("%s Error message text should be as expected!", PAGE_NAME_LOGIN));
        LoginSuccessPage loginSuccessPage = new LoginSuccessPage(AppiumDriverManager.getDriver());
        Assert.assertFalse(loginSuccessPage.isOpened(), String.format("%s Page should not be opened!", PAGE_NAME_LOGIN_SUCCESS));
    }
}
