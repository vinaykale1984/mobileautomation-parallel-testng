package com.qa.stepdef;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParameters;
import com.qa.utils.ServerManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;

public class Hooks {

    @Before
    public void initialize() {
//        GlobalParameters parameters = new GlobalParameters();
//        parameters.initializeGlobalParameters();
//        ThreadContext.put("ROUTINGKEY", parameters.getPlatformName() + "_" + parameters.getDeviceName());
//
//        new ServerManager().startServer();
//        new DriverManager().initializeDriver();

    }

    @After
    public void quit(Scenario scenario) {
        if(scenario.isFailed()) {
            byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

//        DriverManager driverManager = new DriverManager();
//        if (driverManager.getDriver() != null) {
//            driverManager.getDriver().quit();
//        }
//
//        ServerManager serverManager = new ServerManager();
//        if (serverManager.getServer() != null) {
//            serverManager.getServer().stop();
//        }


    }
}
