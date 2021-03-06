
package UATPAXPEGASUS;

import _env.hooks;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import commons.AbstractAPI;
import commons.AbstractPages;
import commons.AbstractSocketEvent;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import interfacePackage.*;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.datatable.DataTable;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Stepdefs {
    AndroidDriver driver;
    AbstractPages abstractPage;
    LoginPO loginPage;
    HomePO homePage;
    SocketEvent socket;
    APIRequest clientAPI;
    commons commons;
    hooks hooks;

    private final Logger LOGGER = LogManager.getLogger( AbstractSocketEvent.class );
    Gson gson = new Gson();

    public Stepdefs() {
        driver = hooks.openPaxApp();
        abstractPage = new AbstractPages(driver);
        loginPage = new LoginPO(driver);
        homePage = new HomePO(driver);
        commons = new commons(driver);
        socket = new AbstractSocketEvent();
        clientAPI = new AbstractAPI();
        abstractPage.sendAppPackage();
    }

    String uri() {
        InputStream inputStream;
        Properties prop = new Properties();
        String propFileName = "config.properties";

        inputStream = AbstractSocketEvent.class.getClassLoader().getResourceAsStream( propFileName );
        if (inputStream != null) {
            try {
                prop.load( inputStream );
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new FileNotFoundException( "property file '" + propFileName + "' not found in the classpath" );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String uri = prop.getProperty( "apiServer" );
        return uri;
    }

//    @After
    public void afterScenario() throws JSONException, UnirestException {
        final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>();
        String token = clientAPI.httpRequestAPI();

        Map<String, String> headers = new HashMap<>();
        headers.put( "Content-Type", "application/json" );
        headers.put( "Authorization", token );

        HttpResponse<JsonNode> response = null;

        JSONObject objBody = new JSONObject();
        objBody.put( "limit", 50 );

        response = Unirest.post( uri() + "/api/booking/find" )
                .headers( headers )
                .body( objBody )
                .asJson();
        JSONObject jsonObject = response.getBody().getObject();
        values.offer( jsonObject );
        try {
            JSONObject args = (JSONObject) values.take();
            assertThat( args.length(), is( 1 ) );

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject resObj = jsonObject.getJSONObject( "res" );
        JSONArray resList = resObj.getJSONArray( "list" );
        JSONObject listObject = resList.getJSONObject( 0 );
        LOGGER.info( "List booking: {}", listObject.toString() );
        String bookId = gson.toJson( listObject.get( "bookId" ) ).toString();
        LOGGER.info( "BookId: {}", bookId );
    }

    @Given("Register and start status receive booking type {string} of driver")
    public void iConnectEvent(String bookType, List<String> table) throws URISyntaxException {
        socket.connectSocket(bookType, table);
    }

    @Given("an api token after logined command center")
    public void httpRequestAPI() throws JSONException, UnirestException {
        clientAPI.httpRequestAPI();
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
        assertTrue(loginPage.isCheckMatchesMessage(table.get(1)));
    }

    @Given("I should open form verify code and send data")
    public void inputSMSDefaultCode(List<String> table) {
        loginPage.inputSMSDefaultCode(table.get(1));
    }

    @Given("I want to get content message in popup")
    public void isGetMessageContent() {
        loginPage.getContentPopup();
    }

    @Given("I want to reset data login passenger with data")
    public void resetRegiter(List<String> table) throws URISyntaxException {
        socket.resetRegiter( table );
    }

    @Given("I want to tap button OK")
    public void clickToOKButton() {
        loginPage.clickToOKButton();
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

    @Given( "I want to get current location customer" )
    public void isCurentLocation(){
        homePage.isCurentLocation();
    }
    // ------------Commons----------------------- //

    @Given("I should get the response data matches with")
    public void iMatchDataJSON(DataTable table) throws JSONException {
        assertTrue(commons.isMacthDataJSON(table));
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
        assertTrue(commons.openHomeScreen());
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
