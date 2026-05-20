package com.nexabank.tests.ui;

import com.nexabank.pages.BillPaymentPage;
import com.nexabank.pages.LoginPage;
import com.nexabank.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BillPaymentTests extends BaseTest {

    @Test(groups = {"regression"})
    public void verifyBillPaymentWorkflow() {
        new LoginPage(driver).login("john", "demo");
        BillPaymentPage billPaymentPage = new BillPaymentPage(driver);
        billPaymentPage.submitBillPayment("Electricity Board", "MG Road", "Pune", "MH", "411001", "9999999999", "123456", "750");

        Assert.assertTrue(billPaymentPage.isPaymentComplete(), "Bill payment confirmation should be displayed");
    }

    @Test(groups = {"regression"})
    public void verifyBillPaymentMandatoryFieldValidation() {
        new LoginPage(driver).login("john", "demo");
        BillPaymentPage billPaymentPage = new BillPaymentPage(driver);
        billPaymentPage.submitEmptyBillPayment();

        Assert.assertTrue(billPaymentPage.isValidationErrorDisplayed(), "Mandatory field validation error should be displayed");
    }
}
