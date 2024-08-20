package com.qa.utils;

import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.util.Properties;

public class CapabilitiesManager {

    TestUtils utils = new TestUtils();


    File f = new File("Apps");
    File androidApp = new File(f, "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
    File iosApp = new File(f, "iosAppName");


    // Below method depriciated
    /*public DesiredCapabilities getCaps() {
        GlobalParameters parameters = new GlobalParameters();
        Properties props = new Properties();

        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", parameters.getPlatformName());
            caps.setCapability("udid", parameters.getUdid());
            caps.setCapability("deviceName", parameters.getDeviceName());


            switch (parameters.getPlatformName()) {
                case "Android":
//                    caps.setCapability("appium:automationName", props.getProperty("androidAutomationName"));
//                    caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
//                    caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
//                    caps.setCapability("systemPort", parameters.getSystemPort());
//                    caps.setCapability("chromeDriverPort", parameters.getChromeDriverPort());
//                    caps.setCapability("appium:app", androidApp.getAbsolutePath());
//                    System.out.println(androidApp.getAbsolutePath());

                    caps.setCapability("automationName", "UiAutomator2");
                    caps.setCapability("appPackage", "swagappsmobileapp");
                    caps.setCapability("appActivity", "com.swagappsmobileapp.SplashActivity");
                    caps.setCapability("systemPort", parameters.getSystemPort());
                    caps.setCapability("chromeDriverPort", parameters.getChromeDriverPort());
                    caps.setCapability("app", androidApp.getAbsolutePath());
                    System.out.println(androidApp.getAbsolutePath());


                    break;
                case "iOS":
                    caps.setCapability("automationName", props.getProperty("iOSAutomationName"));
                    caps.setCapability("bundleId", props.getProperty("iOSBundleId"));
                    caps.setCapability("wdaLocalPort", parameters.getWdaLocalPort());
                    caps.setCapability("webkitDebugProxyPort", parameters.getWebkitDebugProxyPort());
                    caps.setCapability("app", iosApp.getAbsolutePath());
                    break;
            }
            return caps;
        } catch (Exception e) {
            e.printStackTrace();
            utils.log().info("Failed to load capabilities. ABORT!!");
            throw e;
        }
    }*/


    public UiAutomator2Options getOptions() {
        GlobalParameters parameters = new GlobalParameters();
        Properties props = new Properties();
        try {

//            DesiredCapabilities caps = new DesiredCapabilities();

            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName(parameters.getDeviceName())
                    .setUdid(parameters.getUdid())
                    .setDeviceName(parameters.getDeviceName());

            switch (parameters.getPlatformName()) {
                case "Android":
                    options.setAutomationName("UiAutomator2")
                            .setAppPackage("com.swaglabsmobileapp")
                            .setAppActivity("com.swaglabsmobileapp.SplashActivity")
                            .setSystemPort(10000)
                            .setChromedriverPort(11000)
                            .setApp(androidApp.getAbsolutePath());

                            System.out.println("Android app path: " + androidApp.getAbsolutePath());


//                    url = new URL("appiumURL");
//                    driver = new AndroidDriver(url, options);
                    break;

                case "iOS":
                    options.setAutomationName(props.getProperty("iOSAutomationName"))
                            .setAppPackage(props.getProperty("iOSBundleId"))
                            .setApp(iosApp.getAbsolutePath())
                            .setWebviewDevtoolsPort(11001);

//                    url = new URL("appiumURL");
//
//                    driver = new IOSDriver(url, options);
                    break;


            }
            return options;


        } catch (Exception e) {
            e.printStackTrace();
            utils.log().info("Failed to load capabilities. ABORT!!");
            throw e;
        }
    }
}