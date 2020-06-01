
package UATPAXPEGASUS;

import _env.hooks;
import commons.AbstractPages;
import commons.AbstractSocketEvent;
import cucumber.api.java.en.Given;
import interfacePackage.HomePO;
import interfacePackage.LoginPO;
import interfacePackage.commons;
import interfacePackage.SocketEvent;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

public class Stepdefs {
    AndroidDriver driver;
    AbstractPages abstractPage;
    LoginPO loginPage;
    HomePO homePage;
    SocketEvent socket;
    commons commons;

    public Stepdefs() {
        driver = hooks.openPaxApp();
        abstractPage = new AbstractPages(driver);
        loginPage = new LoginPO(driver);
        homePage = new HomePO(driver);
        commons = new commons(driver);
        socket = new AbstractSocketEvent();
        abstractPage.sendAppPackage();
    }

    @Given("^I want to register driver with data$")
    public void iConnectEvent(List<String> table) throws URISyntaxException {
        socket.connectSocket(table);
    }

    @Given("^I want to send request accept booking$")
    public void iAcceptEvent() throws URISyntaxException {
        socket.acceptPreSocketEvent();
    }

    // ------------Login Screen----------------------- //

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
        loginPage.inputToPhoneNumberTextbox(table.get(1));
        takeScreen();
    }

    @Given("I want to tap checkbox term of use")
    public void clickToAgreeToUAndPolicy() {
        loginPage.clickToAgreeToUAndPolicy();
    }

    @Given("I want to tap button Accept form login")
    public void clickToLoginButton() {
        loginPage.clickToLoginButton();
    }

    @Given("I want to tap button {string} form login")
    public void clickToYesNoButton(String string) {
        loginPage.clickToYesNoButton(string);
    }

    @Given("I want to get message error")
    public void getToastMessgae() {
        loginPage.getToastMessages();
    }

    @Given("I should get the response message matches with")
    public void checkMatchesMessage(List<String> table) {
        loginPage.isCheckMatchesMessage(table.get(1));
    }

    @Given("I should open form verify code and send data")
    public void inputSMSDefaultCode(List<String> table) {
        loginPage.inputSMSDefaultCode(table.get(1));
    }

    @Given("I want to get content message in popup")
    public void isGetMessageContent() {
        loginPage.getContentPopup();
    }

    // ------------Home Screen----------------------- //

    @Given("I want to get info menu service")
    public void isMenuService() throws JSONException {
        homePage.isMenuService();
    }

    @Given("I want to open {string} setting")
    public void isOpenSetting(String setting) {
        homePage.isOpenSetting(setting);
    }

    @Given("I want to open {string} in Profile Setting")
    public void isPersonalInfoSetting(String setting) {
        homePage.isPersonalInfoSetting(setting);
    }

    @Given("I want to get info personal info")
    public void isInfoPersonalInfo() throws JSONException {
        homePage.isGetInfoPersonalInfo();
    }

    @Given("I want to edit info account with data")
    public void isInputInfoAcount(List<String> table) {
        homePage.isInputInfoAcount(table);
    }
    // ------------Commons----------------------- //

    @Given("I should get the response message object matches with")
    public void isMatchObject(List<String> table) {
        commons.isCheckMatchesObject(table);
    }

    @Given("I want to open menu setting passenger")
    public void isMenuHome() {
        commons.isMenuHome();
    }

    @Given("I want to touch button Edit")
    public void isActionEdit() {
        commons.isActionEdit();
    }

    @Given("I wan to hide keyboard")
    public void isHideKeyboard() {
        commons.isHideKeyboard();
    }

    @Given("Open application home screen")
    public void openHomeScreen() {
        commons.openHomeScreen();
    }

    public void takeScreen() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filename = UUID.randomUUID().toString();
        File targetFile = new File("src/test/Images/" + filename + ".jpg");
        try {
            FileUtils.copyFile(scrFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
