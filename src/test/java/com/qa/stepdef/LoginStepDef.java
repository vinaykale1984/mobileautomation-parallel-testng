package com.qa.stepdef;

import com.qa.pages.LoginPage;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDef {


    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        new LoginPage().enterPassword(username);

    }

    @When("I enter the password {string}")
    public void iEnterThePassword(String password) {
        new LoginPage().enterPassword(password);

    }

    @When("I login")
    public void iLogin() {
        new LoginPage().pressLoginBtn();

    }

    @When("Login should with an error {string}")
    public void loginShouldWithAnError(String err) {
        Assert.assertEquals(new LoginPage().getErrTxt(), err);

    }

}
