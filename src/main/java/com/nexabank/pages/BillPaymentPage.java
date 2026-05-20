package com.nexabank.pages;

import com.nexabank.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BillPaymentPage extends BasePage {
    @FindBy(linkText = "Bill Pay")
    private WebElement billPayMenu;

    @FindBy(name = "payee.name")
    private WebElement payeeNameInput;

    @FindBy(name = "payee.address.street")
    private WebElement addressInput;

    @FindBy(name = "payee.address.city")
    private WebElement cityInput;

    @FindBy(name = "payee.address.state")
    private WebElement stateInput;

    @FindBy(name = "payee.address.zipCode")
    private WebElement zipInput;

    @FindBy(name = "payee.phoneNumber")
    private WebElement phoneInput;

    @FindBy(name = "payee.accountNumber")
    private WebElement accountInput;

    @FindBy(name = "verifyAccount")
    private WebElement verifyAccountInput;

    @FindBy(name = "amount")
    private WebElement amountInput;

    @FindBy(xpath = "//input[@value='Send Payment']")
    private WebElement sendPaymentButton;

    @FindBy(xpath = "//h1[contains(text(),'Bill Payment Complete')]")
    private WebElement paymentCompleteHeader;

    @FindBy(css = ".error")
    private WebElement validationError;

    public BillPaymentPage(WebDriver driver) {
        super(driver);
    }

    public void openBillPay() {
        click(billPayMenu);
    }

    public void submitBillPayment(String payeeName, String address, String city, String state, String zip,
                                  String phone, String accountNumber, String amount) {
        openBillPay();
        type(payeeNameInput, payeeName);
        type(addressInput, address);
        type(cityInput, city);
        type(stateInput, state);
        type(zipInput, zip);
        type(phoneInput, phone);
        type(accountInput, accountNumber);
        type(verifyAccountInput, accountNumber);
        type(amountInput, amount);
        click(sendPaymentButton);
    }

    public void submitEmptyBillPayment() {
        openBillPay();
        click(sendPaymentButton);
    }

    public boolean isPaymentComplete() {
        return isDisplayed(paymentCompleteHeader);
    }

    public boolean isValidationErrorDisplayed() {
        return isDisplayed(validationError);
    }
}
