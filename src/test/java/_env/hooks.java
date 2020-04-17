package _env;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by nghia.huynht on 12/26/2019.
 */
public class hooks {
    private static AndroidDriver driver;
    private static String packageApp;

    @Before
    public static AndroidDriver openPaxApp(){
        Properties prop = new Properties();
        packageApp = prop.getProperty("packageApp");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "Android Emulator");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "9");
        cap.setCapability("appPackage", "com.mycar.passenger");
        cap.setCapability("appActivity", "com.qup.pegasus.ui.launch.LauncherActivity");
        cap.setCapability("autoGrantPermissions", "true");
        cap.setCapability("skipDeviceInitialization", "true");
        cap.setCapability("skipServerInstallation", "true");
        cap.setCapability("noReset", "true");
        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }
}