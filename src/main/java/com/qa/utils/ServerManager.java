package com.qa.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import java.io.File;
import java.util.HashMap;

public class ServerManager {

    private static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriverLocalService getServer() {
        return server.get();
    }

    public void startServer() {
        utils.log().info("Appium server staring...");
        AppiumDriverLocalService server = WindowsGetAppiumService();
        server.start();
        if (server == null || !server.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("Server not started");
        }
//        server.clearOutPutStreams();  // for not allowing server to print in logs
        this.server.set(server);

        utils.log().info("Appium server started!");
    }

    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    public AppiumDriverLocalService WindowsGetAppiumService() {
        GlobalParameters parameters = new GlobalParameters();
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withLogFile(new File(parameters.getPlatformName() + "_"
                        + parameters.getDeviceName() + File.separator + "Server.log")));
    }

    public AppiumDriverLocalService MacGetAppiumService() {
        GlobalParameters parameters = new GlobalParameters();
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("PATH", "enter_your_path_here" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", "enter_your_android_home_path");
        environment.put("JAVA_HOME", "enter_your_java_home_path");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment)
                .withLogFile(new File(parameters.getPlatformName() + "_"
                        + parameters.getDeviceName() + File.separator + "Server.log")));
    }
}


