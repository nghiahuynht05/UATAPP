package interfacePackage;

import commons.AbstractPages;
import defineUIPackage.DefineUI;
import io.appium.java_client.android.AndroidDriver;

import java.util.List;

public class commons extends AbstractPages {

    public commons(AndroidDriver driver) {
        super(driver);
    }

    public void isCheckMatchesObject(List<String> table) {
        checkMatchesObject(table);
    }

    public void isMenuHome() {
        clickToElementById(DefineUI.HOME_BUTTON);
    }

    public void isActionEdit() {
        clickToElementById(DefineUI.ACTION_EDIT);
    }

    public void isHideKeyboard() {
        isHideKeyboard();
    }

    public boolean openHomeScreen() {
        checkBannerAndClose();
        return checkElementPresentById(DefineUI.HOME_MENU_HOME);
    }
}
