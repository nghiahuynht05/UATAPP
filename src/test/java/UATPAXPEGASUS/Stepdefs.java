package UATPAXPEGASUS;

import _env.hooks;
import commons.AbstractPages;
import commons.AbstractSocket;
import cucumber.api.java.en.Given;
import io.appium.java_client.android.AndroidDriver;
import io.socket.client.Socket;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pagesObject.HomePO;
import pagesObject.LoginPO;
import pagesObject.SocketEvent;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

public class Stepdefs {
    AndroidDriver driver;
    Socket socket;
    AbstractPages abstractPage;
    AbstractSocket abstractSocket;

    LoginPO loginPage;
    HomePO homePage;
    SocketEvent socketEvent;

    public Stepdefs() throws URISyntaxException {
        driver = hooks.openPaxApp();
        socket = hooks.socketEvent();

        abstractPage = new AbstractPages(driver);
        abstractSocket = new AbstractSocket(socket);

        loginPage = new LoginPO(driver);
        homePage = new HomePO(driver);
        socketEvent = new SocketEvent(socket);

        abstractPage.sendAppPackage();
    }

    @Given("^I want to connect beta server$")
    public void iConnectEvent() throws URISyntaxException {
        socketEvent.connectSocketEvent();
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

    @Given("I should get the response message matches with language")
    public void checkMatchesMessage(List<String> table) {
        loginPage.isCheckMatchesMessage(table.get(1));
    }

    @Given("I should open form verify code and send data")
    public void inputSMSDefaultCode(List<String> table) {
        loginPage.inputSMSDefaultCode(table.get(1));
    }

    @Given("I should open Home form")
    public void openHomeScreen() {
        homePage.openHomeScreen();
    }

    public void takeScreen(){
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
