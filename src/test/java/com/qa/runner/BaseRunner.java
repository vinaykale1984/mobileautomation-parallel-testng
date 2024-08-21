package com.qa.runner;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParameters;
import com.qa.utils.ServerManager;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.*;

public class BaseRunner {

    private static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner = new ThreadLocal<>();

    public static TestNGCucumberRunner getRunner(){
        return testNGCucumberRunner.get();
    }

    private static void setRunner(TestNGCucumberRunner testNGCucumberRunner1){
        testNGCucumberRunner.set(testNGCucumberRunner1);
    }

    @Parameters({"platformName", "udid", "deviceName", "systemPort",
            "chromeDriverPort", "wdaLocalPort", "webkitDebugProxyPort"})
    @BeforeClass(alwaysRun = true)
    public void setUpClass(String platformName, String udid, String deviceName,
                           @Optional("Android") int systemPort,
                           @Optional("Android") int chromeDriverPort,
                           @Optional("iOS") String wdaLocalPort,
                           @Optional("iOS") String webkitDebugProxyPort) {
        ThreadContext.put("ROUTINGKEY", platformName + "_" + deviceName);

        GlobalParameters parameters = new GlobalParameters();
        parameters.setPlatformName(platformName);
        parameters.setUdid(udid);
        parameters.setDeviceName(deviceName);


        switch (platformName) {
            case "Android":
                parameters.setSystemPort(systemPort);
                parameters.setChromeDriverPort(chromeDriverPort);
                break;
            case "iOS":
                parameters.setWdaLocalPort(wdaLocalPort);
                parameters.setWebkitDebugProxyPort(webkitDebugProxyPort);
                break;

        }

        new ServerManager().startServer();
        new DriverManager().initializeDriver();

//        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        setRunner(new TestNGCucumberRunner(this.getClass()));
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
        getRunner().runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return getRunner().provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        DriverManager driverManager = new DriverManager();
        if (driverManager.getDriver() != null) {
            driverManager.getDriver().quit();
        }

        ServerManager serverManager = new ServerManager();
        if (serverManager.getServer() != null) {
            serverManager.getServer().stop();
        }
        if (testNGCucumberRunner != null) {
            getRunner().finish();
        }
    }

}
