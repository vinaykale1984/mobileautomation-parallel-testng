package com.qa.utils;

import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.util.Properties;

public class CapabilitiesManager {

    TestUtils utils = new TestUtils();


    File f = new File("Apps");
    File androidApp = new File(f, "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
    File iosApp = new File(f, "iosAppName");

    public UiAutomator2Options getOptions() {
        GlobalParameters parameters = new GlobalParameters();
        Properties props = new Properties();
        try {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName(parameters.getDeviceName())
                    .setUdid(parameters.getUdid())
                    .setDeviceName(parameters.getDeviceName());

            switch (parameters.getPlatformName()) {
                case "Android":
                    options.setAutomationName("UiAutomator2")
                            .setAppPackage("com.swaglabsmobileapp")
                            .setAppActivity("com.swaglabsmobileapp.SplashActivity")
                            .setSystemPort(parameters.getSystemPort())
                            .setChromedriverPort(parameters.getChromeDriverPort())
                            .setApp(androidApp.getAbsolutePath());

                    System.out.println("Android app path: " + androidApp.getAbsolutePath());
                    break;

                case "iOS":
                    options.setAutomationName(props.getProperty("iOSAutomationName"))
                            .setAppPackage(props.getProperty("iOSBundleId"))
                            .setApp(iosApp.getAbsolutePath())
                            .setWebviewDevtoolsPort(11001);
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