package _env;

import commons.AbstractSocketEvent;
import cucumber.api.java.Before;
import interfacePackage.SocketEvent;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by nghia.huynht on 12/26/2019.
 */

public class hooks {
    private static AndroidDriver driver;
    public static Logger LOGGER = LogManager.getLogger(AbstractSocketEvent.class);
    SocketEvent socket;

    @Before
    public static AndroidDriver openPaxApp() {
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
            String packageApp = prop.getProperty("packageApp");
            LOGGER.info("packageApp: {}", packageApp);
            DesiredCapabilities cap = new DesiredCapabilities();

            cap.setCapability("deviceName", "Android Emulator");
            cap.setCapability("platformName", "Android");
            cap.setCapability("platformVersion", "5.1.1");
            cap.setCapability("appPackage", "com.mycar.passenger");
            cap.setCapability("appActivity", "com.qup.pegasus.ui.launch.LauncherActivity");
            cap.setCapability("autoGrantPermissions", "true");
            cap.setCapability("skipDeviceInitialization", "true");
            cap.setCapability("skipServerInstallation", "true");
            cap.setCapability("noReset", "true");
            try {
                driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }
}