package com.qa.runner;

import io.cucumber.testng.CucumberOptions;

/**
 * An example of using TestNG when the test class does not inherit from
 * AbstractTestNGCucumberTests but still executes each scenario as a separate
 * TestNG test.
 */
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports/Pixel8/Cucumber.html",
//                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "summary"
        },
        features = {"src/test/resources/features"},
        glue = {"com.qa.stepdef"},
        monochrome = true,
        dryRun = false,
        tags = "@Launch"

)

public class MyPixel8TestNGRunnerTest extends BaseRunner {



}