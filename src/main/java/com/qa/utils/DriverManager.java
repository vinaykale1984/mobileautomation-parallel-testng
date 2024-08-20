package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverManager {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    TestUtils utils = new TestUtils();

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }

    public void initializeDriver() {
        AppiumDriver driver = null;
        GlobalParameters parameters = new GlobalParameters();
        PropertyManager props = new PropertyManager();

        if (driver == null) {
            try {
                utils.log().info("Initializing driver..!!");
                switch (parameters.getPlatformName()) {
                    case "Android":
//                        driver = new AndroidDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCaps());
                        driver = new AndroidDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getOptions());
                        break;
                    case "iOS":
                        driver = new IOSDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getOptions());
                        break;

                }
                utils.log().info("Driver initialized");
                this.driver.set(driver);
            } catch (Exception e) {
                e.printStackTrace();
                utils.log().info("Driver initialization failed" + e.toString());
                throw new RuntimeException(e);

            }
//            catch (IOException e) {
//                e.printStackTrace();
//                utils.log().info("Driver initialization failed" + e.toString());
//                throw e;
//            }
        }
    }
}
