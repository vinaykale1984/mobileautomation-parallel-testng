package com.qa.runner;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParameters;
import com.qa.utils.ServerManager;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.ThreadContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports/Cucumber.html",
//                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "summary"
        },
        snippets = CAMELCASE,
        features = {"src/test/resources/features"},
//        glue = {"src/test/java/com/qa/stepdef"},
        glue = {"com.qa.stepdef"},
        monochrome = true,
        dryRun = false,
        stepNotifications = true
        ,tags = "@Launch"

)
public class RunnerTest {

        @BeforeClass
        public static void initialize(){
                GlobalParameters parameters = new GlobalParameters();
                parameters.initializeGlobalParameters();
                ThreadContext.put("ROUTINGKEY", parameters.getPlatformName() + "_" + parameters.getDeviceName());

                new ServerManager().startServer();
                new DriverManager().initializeDriver();
        }

        @AfterClass
        public static void quit(){

                DriverManager driverManager = new DriverManager();
                if (driverManager.getDriver() != null) {
                        driverManager.getDriver().quit();
                }

                ServerManager serverManager = new ServerManager();
                if (serverManager.getServer() != null) {
                        serverManager.getServer().stop();
                }
        }

}
