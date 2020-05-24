package _env;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by nghia.huynht on 12/26/2019.
 */
public class hooks {
    private static AndroidDriver driver;
    private static Socket socket;

    private static String packageApp;

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
            System.out.println(packageApp);
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

    @Before
    public static Socket socketEvent() {
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        opts.reconnection = false;
        try {
            socket = IO.socket("https://dispatch.beta.qup.vn", opts);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
//        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
//            @Override
//            public void call(Object... objects) {
//                System.out.println("connect = " + objects);
//                JSONObject phone = new JSONObject();
//                try {
//                    phone.put("number", "7400123456");
//                    phone.put("country", "GB");
//                } catch (
//                        JSONException e) {
//                    e.printStackTrace();
//                }
//
//                JSONObject obj = new JSONObject();
//                try {
//                    obj.put("platform", "android");
//                    obj.put("phone", phone);
//                    obj.put("fleetId", "haidr");
//                    obj.put("appType", "driver");
//                    obj.put("verifyCode", "3210");
//                    obj.put("ime", "xxx");
//                    obj.put("password", "password");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                socket.emit("register", obj);
//                socket.on("register", new Emitter.Listener() {
//                    @Override
//                    public void call(Object... objects) {
//                        JSONObject obj = (JSONObject) objects[0];
//                        System.out.println("register = " + obj);
//                    }
//                });
//            }
//        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
//            @Override
//            public void call(Object... objects) {
//                System.out.println(objects);
//            }
//        });
        socket.connect();
        return socket.connect();
    }
}