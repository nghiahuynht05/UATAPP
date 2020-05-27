package interfacePackage;

import com.gargoylesoftware.htmlunit.AbstractPage;
import commons.AbstractPages;
import defineUIPackage.DefineUI;
import io.appium.java_client.android.AndroidDriver;

/**
 * Created by nghia.huynht on 4/17/2020.
 */
public class LoginPO extends AbstractPages {

    private AbstractPage abstractPage;
    String phoneCode;

    public LoginPO(AndroidDriver driver) {
        super(driver);
    }

    public void inputToFleetCodeDefault(String fleetCode) {
        sendKeyToElementById(DefineUI.FLEET_CODE_TEXTBOX_DEFAULT, fleetCode);
    }

    public void clickToPhoneNumberTextbox() {
        if (checkElementPresentById(DefineUI.LOGIN_EDIT_HONEWC_TEXTBOX)) {
            clickToElementById(DefineUI.LOGIN_EDIT_HONEWC_TEXTBOX);
        }
    }

    public void longPressToDebugArea() {
        longPressToElementById(DefineUI.DEBUG_AREA);
    }

    public void inputToPassCodeTextbox(String passCode) {
        sendKeyToElementById(DefineUI.PASS_CODE_TEXTBOX, passCode);
    }

    public void clickToYesNoButton(String yesNoValue) {
        clickToElementByXpath(DefineUI.YES_NO_BUTTON, yesNoValue);
    }

    public void selectServer(String server, String fleetCode) {
        if (server.equalsIgnoreCase("local")) {
            clickToElementByXpath(DefineUI.DYNAMIC_SERVERS_TAB, "Local");
            sendKeyToElementById(DefineUI.FLEET_ID_TEXTBOX, fleetCode);
            clickToElementById(DefineUI.APPLY_BUTTON);
        } else if (server.equalsIgnoreCase("lab")) {
            clickToElementByXpath(DefineUI.DYNAMIC_SERVERS_TAB, "Lab");
            sendKeyToElementById(DefineUI.FLEET_ID_TEXTBOX, fleetCode);
            clickToElementById(DefineUI.APPLY_BUTTON);
        } else if (server.equalsIgnoreCase("beta")) {
            clickToElementByXpath(DefineUI.DYNAMIC_SERVERS_TAB, "Beta");
            sendKeyToElementById(DefineUI.FLEET_ID_TEXTBOX, fleetCode);
            clickToElementById(DefineUI.APPLY_BUTTON);
        } else if (server.equalsIgnoreCase("us")) {
            clickToElementByXpath(DefineUI.DYNAMIC_SERVERS_TAB, "QUp");
            sendKeyToElementById(DefineUI.FLEET_ID_TEXTBOX, fleetCode);
            clickToElementById(DefineUI.APPLY_BUTTON);
        } else if (server.equalsIgnoreCase("asap")) {
            clickToElementByXpath(DefineUI.DYNAMIC_SERVERS_TAB, "ASAP");
            sendKeyToElementById(DefineUI.FLEET_ID_TEXTBOX, fleetCode);
            clickToElementById(DefineUI.APPLY_BUTTON);
        } else if (server.equalsIgnoreCase("avis")) {
            clickToElementByXpath(DefineUI.DYNAMIC_SERVERS_TAB, "Avis");
            sendKeyToElementById(DefineUI.FLEET_ID_TEXTBOX, fleetCode);
            clickToElementById(DefineUI.APPLY_BUTTON);
        } else if (server.equalsIgnoreCase("sea")) {
            clickToElementByXpath(DefineUI.DYNAMIC_SERVERS_TAB, "Sing");
            sendKeyToElementById(DefineUI.FLEET_ID_TEXTBOX, fleetCode);
            clickToElementById(DefineUI.APPLY_BUTTON);
        } else {
            System.out.println("Please enter the server that you want to test");
        }
    }

    public void inputToPhoneNumberTextbox(String phoneNumber) {
        sendKeyToElementById(DefineUI.LOGIN_EDITPHONE_TEXTBOX, phoneNumber);
    }

    public void clickToAgreeToUAndPolicy() {
        clickToElementById(DefineUI.LOGIN_ACCEPTTERM_CHECKBOX);
    }

    public void clickToLoginButton() {
        clickToElementById(DefineUI.LOGIN_BUTTON);
    }

    public void inputSMSDefaultCode(String smsCode) {
        if (checkElementPresentById(DefineUI.LOGIN_VERIFY_CODE) == true) {
            sendKeyToElementById(DefineUI.LOGIN_VERIFY_CODE, smsCode);
        }
    }

    public boolean isThereHomeButtonPresent() {
        return checkElementPresentById(DefineUI.HOME_BUTTON);
    }

    public boolean isLoginPagePresent() {
        return checkElementPresentById(DefineUI.LOGIN_ACCEPTTERM_CHECKBOX);
    }

    public boolean isWelcomeMsgContains(String expectedText) {
        return isElementTextContainsById(DefineUI.WELCOME_MESSAGE, expectedText);
    }

    public void selectPhoneCode(String countryName) {
        if (countryName.equalsIgnoreCase("Vietnam")) {
            phoneCode = "+84";
        } else if (countryName.equalsIgnoreCase("Malaysia")) {
            phoneCode = "+60";
        } else if (countryName.equalsIgnoreCase("United State")) {
            phoneCode = "+1";
        }
        String phoneCodeValue = getTextElementById(DefineUI.PHONE_CODE_LABEL);
        //Only change country phone code if the current phone code different with the phone code expected
        if (!phoneCodeValue.equals(phoneCode)) {
            clickToElementById(DefineUI.SELECT_PHONE_CODE_BUTTON);
            sendKeyToElementById(DefineUI.SEARCH_COUNTRY_PHONE_TEXTBOX, countryName);
            clickToElementByXpath(DefineUI.COUNTRY_PHONE_CODE, countryName);
        }
    }

    public void inputToRegisterTextboxes(String nameField, String textValue) {
        if (checkElementPresentByXpath(DefineUI.REGISTER_TEXTBOXES, nameField)) {
            checkElementPresentByXpath(DefineUI.REGISTER_TEXTBOXES, nameField);
            sendKeyToElementByXpath(DefineUI.REGISTER_TEXTBOXES, textValue, nameField);
        }
    }

    public void selectGender(String genderValue) {
        if (checkElementPresentByXpath(DefineUI.REGISTER_TEXTBOXES, "Gender")) {
            clickToElementByXpath(DefineUI.REGISTER_TEXTBOXES, "Gender");
            clickToElementByXpath(DefineUI.GENDER_BUTTONS, genderValue);
        }
    }

    public void clickToSaveButton() {
        clickToElementById(DefineUI.SAVE_BUTTON);
    }

    public void clickToSkipButton() {
        if (checkElementPresentById(DefineUI.SKIP_BUTTON)) {
            clickToElementById(DefineUI.SKIP_BUTTON);
        }
    }

    public boolean isCurrentlyLoggedIn() {
        return checkElementPresentById(DefineUI.HOME_BUTTON);
    }

    public void getToastMessages() {
        getToastMessage(DefineUI.LOGIN_TOAST_MESSAGE);
    }

    public boolean isCheckMatchesMessage(String expectData ){
        return checkMatchesMessage(expectData );
    }
}
