package UATPAXPEGASUS;

import _env.hooks;
import commons.AbstractPages;
import commons.AbstractSocket;
import cucumber.api.java.en.Given;
import io.appium.java_client.android.AndroidDriver;
import pagesObject.HomePO;
import pagesObject.LoginPO;
import pagesObject.SocketEvent;

import java.net.URISyntaxException;
import java.util.List;

public class Stepdefs {
    AndroidDriver driver;
    AbstractPages abstractPage;
    LoginPO loginPage;
    HomePO homePage;
    SocketEvent socket;

    public Stepdefs() throws URISyntaxException {
        driver = hooks.openPaxApp();
        abstractPage = new AbstractPages(driver);
        loginPage = new LoginPO(driver);
        homePage = new HomePO(driver);
        socket = new AbstractSocket();
        abstractPage.sendAppPackage();
    }

    @Given("^I want to connect beta server$")
    public void iConnectEvent() throws URISyntaxException {
        socket.connectSocket("https://dispatch.beta.qup.vn");
    }
    @Given("^I want to disconect beta server$")
    public void iDisconnectEvent() throws URISyntaxException {
        socket.disconectSocketEvent("https://dispatch.beta.qup.vn");
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
