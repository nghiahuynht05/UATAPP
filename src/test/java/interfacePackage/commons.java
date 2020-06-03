package interfacePackage;

import commons.AbstractPages;
import defineUIPackage.DefineUI;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.datatable.DataTable;
import org.json.JSONException;

public class commons extends AbstractPages {

    public commons(AndroidDriver driver) {
        super(driver);
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
        return checkElementPresentById(DefineUI.HOME_MENU_HOME);
    }
    public boolean isMacthDataJSON(DataTable dataTable) throws JSONException {
        return MacthDataJSON(dataTable);
    }
}
