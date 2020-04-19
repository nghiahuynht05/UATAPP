package UATPAXPEGASUS;

import _env.hooks;
import cucumber.api.java.en.Given;

import io.appium.java_client.android.AndroidDriver;

import commons.AbstractPages;
import pagesObject.*;

import java.util.List;

public class Stepdefs {
    AndroidDriver driver;
    AbstractPages abstractPage;
    LoginPO loginPage;
    HomePO homePage;

    public Stepdefs(){
        driver = hooks.openPaxApp();
        abstractPage = new AbstractPages(driver);
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

    @Given("I want to input phone number form login with data")
    public void loginSetPhoneNumber(List<String> table) {
        loginPage.clickToPhoneNumberTextbox();
        loginPage.inputToPhoneNumberTextbox(table.get(0));
    }
}
