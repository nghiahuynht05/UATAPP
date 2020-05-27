package interfacePackage;

import io.appium.java_client.android.AndroidDriver;
import commons.*;
import defineUIPackage.DefineUI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nghia.huynht on 4/17/2020.
 */
public class HomePO extends AbstractPages {

    private AbstractPages abstractPage;
    String currentCarName;

    public HomePO(AndroidDriver driver) {
        super(driver);
    }

    public void logout() {
        clickToElementById(DefineUI.HOME_BUTTON);
        clickToElementById(DefineUI.PERSONAL_BUTTON);
        checkBannerAndClose();
        clickToElementById(DefineUI.LOGOUT_BUTTON);
    }

    public boolean openHomeScreen(){
        return checkElementPresentById(DefineUI.HOME_MENU_HOME);
    }
    public boolean isLoginFormDisplayed() {
        return checkElementPresentById(DefineUI.LOGIN_EDIT_HONEWC_TEXTBOX);
    }

    public boolean isCarImgDisplayed() {
        return isElementDisplayedById(DefineUI.CAR_IMAGE);
    }

    public boolean isCarNameDisplayed() {
        return isElementDisplayedById(DefineUI.CAR_NAME);
    }

    public boolean isCarMaxOfSeatDisplayed() {
        return isElementDisplayedById(DefineUI.MAX_OF_SEAT);
    }

    public boolean isPUEqualsCurrentLocation() {
        String currentGPS = getTextElementById(DefineUI.CURRENT_LOCATION_LABEL);
        if (currentGPS.contains("271 Nguyễn Văn Linh")) {
            return true;
        } else if (currentGPS.contains("Tầng 7, tòa nhà Bưu điện")) {
            return true;
        } else if (currentGPS.contains("155C Nguyễn Văn Linh")) {
            return true;
        } else if (currentGPS.contains("322 Hải Phòng")) {
            return true;
        } else if (currentGPS.contains("Tầng 4 tòa nhà Bưu điện")) {
            return true;
        } else {
            return false;
        }
    }

    public void moveMap() {
        actionMove(498, 681, 42, 84);
    }

    public boolean isThePUWasChanged() {
        return checkElementPresentById(DefineUI.BACK_TO_CURRENT_GPS_BUTTON);
    }

    public void clickToCurrentGPSButton() {
        clickToElementById(DefineUI.BACK_TO_CURRENT_GPS_BUTTON);
    }

    public void clickToViewAllButton() {
        clickToElementById(DefineUI.VIEW_ALL_BUTTON);
    }

    public void clickToCarImage() {
        clickToElementById(DefineUI.CAR_IMAGE);
    }

    public boolean swipeCarType() {
        currentCarName = getTextElementById(DefineUI.CAR_NAME);
        actionMove(920, 1594, 145, 1594);
        String swipedCarName = getTextElementById(DefineUI.CAR_NAME);
        return (!currentCarName.equals(swipedCarName));
    }

    public boolean isMaxLuggageDisplayed() {
        return checkElementPresentById(DefineUI.MAX_OF_LUGGAGE);
    }

    public boolean isMinimumFareDisplayed() {
        return checkElementPresentById(DefineUI.MINIMUM_FARE_LABEL);
    }

    public boolean isBaseFareDisplayed() {
        return checkElementPresentById(DefineUI.BASE_FARE_LABEL);
    }

    public boolean isFeePerKMDisplayed() {
        return checkElementPresentById(DefineUI.FARE_PER_KM_LABEL);
    }

    public boolean isFeePerMinuteDisplayed() {
        return checkElementPresentById(DefineUI.FARE_PER_MINUTE_LABEL);
    }

    public boolean isNoteDescriptionDisplayed() {
        return checkElementPresentById(DefineUI.NOTE_DESCRIPTION);
    }

    public void clickToSelectButton() {
        clickToElementById(DefineUI.SELECT_CAR_BUTTON);
    }

    public boolean isCurrentCarTypeEquals(String carName) {
        String currentCar = getTextElementById(DefineUI.CAR_NAME);
        return currentCar.equalsIgnoreCase(carName);
    }

    public boolean isPickUpTimeIsNow() {
        String pickuptype = getTextElementById(DefineUI.PICKUP_TYPE_NOW);
        return pickuptype.equalsIgnoreCase("Now");
    }

    public boolean isCarTypeReservationOnly() {
        clickToElementById(DefineUI.PICKUP_TYPE);
        return checkElementIsNotPresentById(DefineUI.BOOK_NOW_BUTTON);
    }

    public void selectcarType(String carName) {
        clickToElementByXpath(DefineUI.CAR_TYPE_DYNAMIC, carName);
    }

    public boolean isCarTypeBothOnDemandAndReservation() {
        clickToElementById(DefineUI.PICKUP_TYPE);
        return checkElementPresentById(DefineUI.BOOK_NOW_BUTTON);
    }

    public void clickToBackIcon() {
        clickToElementById(DefineUI.BACK_ICON);
    }

    public void selectPickUpTimeIsNow() {
        clickToElementById(DefineUI.PICKUP_TYPE);
        clickToElementById(DefineUI.BOOK_NOW_BUTTON);
    }

    public void selectPickUpTimeIsDateTime() {
        clickToElementById(DefineUI.PICKUP_TYPE_NOW);
        actionMove(235, 891, 235, 718);
        clickToDynamicButton("Set time");
    }

    public boolean isPUTimeIsDateTime() {
        Date now = new Date();
        String systemTime = new SimpleDateFormat("dd, HH:mm", Locale.ENGLISH).format(now);

        String picUpTime = getTextElementById(DefineUI.PICKUP_TYPE_NOW);
        System.out.println("Pick up time: " + picUpTime);
        System.out.println("System time: " + systemTime);
        return picUpTime.contains(systemTime);
    }

    public boolean isNextButtonPresent(){
        return isElementDisplayedById(DefineUI.NEXT_BUTTON_SKIP_DO);
    }

    public boolean isNextButtonNotDisplay(){
        return checkElementIsNotPresentById(DefineUI.NEXT_BUTTON_SKIP_DO);
    }

    public void clickToPUAddress(){
        clickToElementById(DefineUI.CURRENT_LOCATION_LABEL);
    }

    public void inputToPUAddress(String pickupAddress){
        sendKeyToElementById(DefineUI.PICKUP_ADDRESS_TEXTBOX, pickupAddress);
    }

    public void selectPUFromSuggest(String addressContains){
        clickToElementByXpath(DefineUI.ADDRESS_SUGGESTED, addressContains);
    }

    public void inputToDOAddress(String destinationAddress){
        sendKeyToElementById(DefineUI.DESTINATION_ADDRESS_TEXTBOX, destinationAddress);
    }

    public void selectDOFromSuggest(String addressContains){
        clickToElementByXpath(DefineUI.ADDRESS_SUGGESTED, addressContains);
    }

    public boolean isAddressFormatted(String expectedAddress){
        String actualAddress = getTextElementById(DefineUI.PICKUP_ADDRESS_TEXTBOX);
        System.out.println(actualAddress);
        return actualAddress.contains(expectedAddress);
    }

    public boolean isResultOrdered3rd(String numberOfResult, String resultValue){
        String firstAddressResult = getTextElementByXpath(DefineUI.ADDRESS_RESULT_NUMBER, numberOfResult);
        System.out.println(firstAddressResult);
        return firstAddressResult.contains(resultValue);
    }

    public void clickToDestinationOnHome(){
        clickToElementById(DefineUI.DESTINATION_ADDRESS_TEXTBOX);
    }

    public boolean isDOPinDisplayed(){
        return checkElementDisplayedByXpath(DefineUI.DO_PIN);
    }
}
