package UATPAXPEGASUS;

import _env.hooks;
import cucumber.api.java.en.Given;

import io.appium.java_client.android.AndroidDriver;

import commons.abstractPages;
import pagesObject.*;

public class Stepdefs {
    AndroidDriver driver;
    abstractPages abstractPage;
    LoginPO loginPage;
    HomePO homePage;

    public Stepdefs (){
        driver = hooks.openPaxApp();
        abstractPage = new abstractPages(driver);
        loginPage = new LoginPO(driver);
        homePage = new HomePO(driver);

        abstractPage.sendAppPackage();
    }
    @Given("^I logout if currently logged in$")
    public void iLogoutIfCurrentlyLoggedIn() {
        abstractPage.sleepInSecond(1);
        if (loginPage.isCurrentlyLoggedIn()) {
            homePage.logout();
            abstractPage.sleepInSecond(1);
            loginPage.clickToPhoneNumberTextbox();
        } else {
            loginPage.clickToPhoneNumberTextbox();
        }
    }
    @Given("^I click to phone number text box$")
    public void iClickToPhoneNumberTextBox() {

        loginPage.clickToPhoneNumberTextbox();
    }

}
