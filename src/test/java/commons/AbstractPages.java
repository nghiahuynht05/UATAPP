package commons;

import _env.hooks;
import defineUIPackage.AbstractPagesUI;
import defineUIPackage.DefineUI;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.datatable.DataTable;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by nghia.huynht on 4/17/2020.
 */
public class AbstractPages {
    AndroidDriver driver;
    Actions actions;
    WebElement element;
    List<WebElement> elements;
    AbstractPagesUI abstractUI;
    public long longTimeout = 30;
    public long shortTimeout = 3;
    public static String appPackageId, appName;
    public String appPackage = "mycar";
    public String returnData = "";
    private static final Logger LOGGER = LogManager.getLogger(AbstractSocketEvent.class);
    public JSONObject responseData = new JSONObject();
    public JSONObject expectData = new JSONObject();

    public AbstractPages(AndroidDriver driver) {
        this.driver = driver;
        abstractUI = new AbstractPagesUI();
    }

    // Internal methods
    public static WebElement waitElementToBeClickableByLocator(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean MacthDataJSON(DataTable dataTable) throws JSONException {

        responseData.put("bookId", "123");
        for (Map<Object, Object> data : dataTable.asMaps(String.class, String.class)) {
            try {
                expectData.put("serviceNow", data.get("serviceNow"));
                expectData.put("serviceLate", data.get("serviceLate"));
                expectData.put("serviveHourly", data.get("serviveHourly"));
                expectData.put("serviceSuperHelper", data.get("serviceSuperHelper"));
                expectData.put("serviceNow", data.get("serviceNow"));
                expectData.put("firstName", data.get("firstName"));
                expectData.put("serviceNow", data.get("serviceNow"));
                expectData.put("lastName", data.get("lastName"));
                expectData.put("serviceNow", data.get("serviceNow"));
                expectData.put("phoneNumber", data.get("phoneNumber"));
                expectData.put("serviceNow", data.get("serviceNow"));
                expectData.put("email", data.get("email"));
                expectData.put("national", data.get("national"));
                expectData.put("serviceNow", data.get("serviceNow"));
                expectData.put("gender", data.get("gender"));
                expectData.put("serviceNow", data.get("serviceNow"));
                expectData.put("birthDate", data.get("lastName"));
                expectData.put("serviceNow", data.get("serviceNow"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("Expected data: {}", expectData);
        LOGGER.info("Response data: {}", responseData);
        String expectDateString = expectData.toString();
        String responseDateString = responseData.toString();
        if (expectDateString.equals(responseDateString)) {
            LOGGER.info("Expected data matching is respone data");
            return true;
        } else {
            LOGGER.info("Expected data NOT matching is respone data");
            return false;
        }
    }

    public JSONObject getMenuService() throws JSONException {
        String serviceNow = getTextElementByXpath(DefineUI.HOME_MENU_SERVICE, "Now");
        String serviceLate = getTextElementByXpath(DefineUI.HOME_MENU_SERVICE, "Later");
        String serviveHourly = getTextElementByXpath(DefineUI.HOME_MENU_SERVICE, "Hourly");
        String serviceSuperHelper = getTextElementByXpath(DefineUI.HOME_MENU_SERVICE, "SuperHelper");
        String serviceIntercity = getTextElementByXpath(DefineUI.HOME_MENU_SERVICE, "Intercity");
        responseData.put("serviceNow", serviceNow);
        responseData.put("serviceLate", serviceLate);
        responseData.put("serviveHourly", serviveHourly);
        responseData.put("serviceSuperHelper", serviceSuperHelper);
        responseData.put("serviceIntercity", serviceIntercity);
        return responseData;
    }

    public String getContentMessage(String locator) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        String returnData = element.getText();
        System.out.printf("return data" + returnData);
        return returnData;
    }

    public void getToastMessage(String locator) {
        element = driver.findElement(By.xpath(locator));
        returnData = element.getText();
    }

    public JSONObject GetInfoPersonalInfo() throws JSONException {
        String firstName = getTextElementById(DefineUI.HOME_PROFILE_INFO_FIRSTNAME);
        String lastName = getTextElementById(DefineUI.HOME_PROFILE_INFO_LASTNAME);
        String phoneNumber = getTextElementById(DefineUI.HOME_PROFILE_INFO_PHONE);
        String email = getTextElementById(DefineUI.HOME_PROFILE_INFO_EMAIL);
        String national = getTextElementById(DefineUI.HOME_PROFILE_NATIONAL);
        String gender = getTextElementById(DefineUI.HOME_PROFILE_INFO_GENDER);
        String birthDate = getTextElementById(DefineUI.HOME_PROFILE_INFO_BIRTHDAY);
        responseData.put("firstName", firstName);
        responseData.put("lastName", lastName);
        responseData.put("phoneNumber", phoneNumber);
        responseData.put("email", email);
        responseData.put("national", national);
        responseData.put("gender", gender);
        responseData.put("birthDate", birthDate);
        return responseData;
    }

    public void sleepInSecond(long numberInSecond) {
        try {
            Thread.sleep(numberInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendAppPackage() {
        InputStream inputStream;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = hooks.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            appPackageId = prop.getProperty("appPackageId");
            appName = prop.getProperty("appName");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendKeyToElementById(String locator, String textValue) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        element.clear();
        element.sendKeys(textValue);
    }

    public void sendKeyToElementByXpath(String xpathLocator, String textValue, String attributeValue) {
        xpathLocator = String.format(xpathLocator, attributeValue);
        element = driver.findElement(By.xpath(xpathLocator));
        element.clear();
        element.sendKeys(textValue);
    }

    public void clickToElementById(String locator) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        waitElementToBeClickableByLocator(driver, element).click();
    }

    public void clickToElementByXpath(String xpathLocator) {
        element = driver.findElement(By.xpath(xpathLocator));
        waitElementToBeClickableByLocator(driver, element).click();
    }

    public void clickToElementByXpath(String xpathLocator, String attributeValue) {
        xpathLocator = String.format(xpathLocator, attributeValue);
        element = driver.findElement(By.xpath(xpathLocator));
        waitElementToBeClickableByLocator(driver, element).click();
    }

    public void longPressToElementById(String locator) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        element = driver.findElement(By.id(locator));
        actions = new Actions(driver);
        actions.clickAndHold(element);
        actions.perform();
    }

    public String getTextElementById(String locator) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        String actualText = element.getText();
        return actualText;
    }

    public boolean checkMatchesMessage(String expectData) {
        returnData = "02";
        LOGGER.info("tesst");
        if (returnData.equals(expectData)) {
            LOGGER.info("tesst");
            return true;
        } else {
            LOGGER.info("tesst");
            return false;
        }
    }

    public String getTextElementByXpath(String xpathLocator, String attributeValue) {
        xpathLocator = String.format(xpathLocator, attributeValue);
        element = driver.findElement(By.xpath(xpathLocator));
        if (element.getText()!= null) {
            return element.getText();
        } else {
            return null;
        }
    }

    public boolean checkElementPresentById(String locator) {
        final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>();

        driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
        locator = String.format(locator, appPackageId);
        elements = driver.findElements(By.id(locator));
        values.offer(elements);
        try {
            values.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
//        checkBannerAndClose();
        if (elements.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkElementDisplayedByXpath(String xpathLocator) {
        driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
        element = driver.findElement(By.xpath(xpathLocator));
        return element.isDisplayed();
    }

    public boolean checkElementIsNotPresentById(String locator) {
        driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
        locator = String.format(locator, appPackageId);
        elements = driver.findElements(By.id(locator));
        driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
        if (elements.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkElementPresentByXpath(String xpathLocator, String attributeValue) {
        driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
        xpathLocator = String.format(xpathLocator, attributeValue);
        elements = driver.findElements(By.xpath(xpathLocator));
        driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
        if (elements.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkElementPresentByXpath(String xpathLocator) {
        driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
        elements = driver.findElements(By.xpath(xpathLocator));
        driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
        if (elements.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkElementDisplayedById(AndroidDriver driver, String locator, int time) {
        try {
            By elementId = By.id(locator);
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementId));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplayedById(String locator) {
        locator = String.format(locator, appPackageId);
        element = driver.findElementById(locator);
        return element.isDisplayed();
    }

    public boolean isElementTextContainsById(String locator, String expectedText) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        String actualMsg = element.getText();
        return actualMsg.contains(expectedText);
    }

    public void checkBannerAndClose() {
        boolean bannerDisplay = checkElementPresentById(abstractUI.BANNER);
        if (bannerDisplay == true) {
            String locator = String.format(abstractUI.BANNER_CLOSE_BUTTON, appPackageId);
            element = driver.findElement(By.id(locator));
            element.click();
        }
    }

    public void actionMove(int xmoveFrom, int ymoveFrom, int xmoveTo, int ymoveTo) {
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(xmoveFrom, ymoveFrom));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)));
        action.moveTo(PointOption.point(xmoveTo, ymoveTo));
        action.release();
        action.perform();
        sleepInSecond(1);
    }

    public void isHideKeyboard() {
        driver.hideKeyboard();
    }

    public void doubleTapToElement(String locator) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        TouchActions action = new TouchActions(driver);
        action.doubleTap(element);
        action.perform();
    }

    public void waitToLoadData(String locator) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        element = driver.findElement(By.id(locator));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickToDynamicButton(String textOfButton) {
        clickToElementByXpath(AbstractPagesUI.DYNAMIC_BUTTON, textOfButton);
    }

    public void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("C:/Users/tam.nguyen/Desktop/Screenshot/Appium" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
