package com.nexabank.tests.ui;

import com.fasterxml.jackson.databind.JsonNode;
import com.nexabank.pages.AccountsOverviewPage;
import com.nexabank.pages.LoginPage;
import com.nexabank.tests.base.BaseTest;
import com.nexabank.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"})
    public void verifyLoginPageLoadsSuccessfully() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isRegisterLinkDisplayed(), "Register link should be visible on login page");
    }

    @Test(priority = 2, groups = {"smoke", "regression"})
    public void verifyValidUserLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("john", "demo");

        AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(driver);
        Assert.assertTrue(accountsOverviewPage.isAccountsOverviewDisplayed(), "Accounts overview should be displayed after login");
    }

    @Test(priority = 3, groups = {"regression"}, dataProvider = "invalidLoginData")
    public void verifyInvalidLoginErrorMessage(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        Assert.assertTrue(loginPage.getErrorMessage().toLowerCase().contains("error"),
                "Error message should be displayed for invalid login");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        JsonNode invalidUser = JsonUtils.readJson("src/test/resources/testdata/loginData.json").get("invalidUser");
        return new Object[][]{
                {invalidUser.get("username").asText(), invalidUser.get("password").asText()},
                {"", "demo"},
                {"john", ""}
        };
    }
}
