package com.nexabank.tests.ui;

import com.nexabank.pages.AccountsOverviewPage;
import com.nexabank.pages.LoginPage;
import com.nexabank.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountOverviewTests extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void verifyAccountOverviewAfterLogin() {
        new LoginPage(driver).login("john", "demo");
        AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(driver);

        Assert.assertTrue(accountsOverviewPage.isAccountsOverviewDisplayed(),
                "Account overview table should be visible for authenticated user");
    }

    @Test(groups = {"regression"})
    public void verifyLogoutEndsUserSession() {
        new LoginPage(driver).login("john", "demo");
        AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(driver);
        accountsOverviewPage.logout();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isRegisterLinkDisplayed(), "User should be redirected to login page after logout");
    }
}
