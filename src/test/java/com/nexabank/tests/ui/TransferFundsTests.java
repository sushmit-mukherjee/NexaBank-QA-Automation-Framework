package com.nexabank.tests.ui;

import com.nexabank.pages.LoginPage;
import com.nexabank.pages.TransferFundsPage;
import com.nexabank.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransferFundsTests extends BaseTest {

    @Test(groups = {"regression"})
    public void verifyFundTransferWorkflow() {
        new LoginPage(driver).login("john", "demo");
        TransferFundsPage transferFundsPage = new TransferFundsPage(driver);
        transferFundsPage.transferFunds("100");

        Assert.assertTrue(transferFundsPage.isTransferComplete(), "Transfer complete confirmation should be displayed");
    }
}
