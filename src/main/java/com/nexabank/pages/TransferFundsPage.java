package com.nexabank.pages;

import com.nexabank.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransferFundsPage extends BasePage {
    @FindBy(linkText = "Transfer Funds")
    private WebElement transferFundsMenu;

    @FindBy(id = "amount")
    private WebElement amountInput;

    @FindBy(xpath = "//input[@value='Transfer']")
    private WebElement transferButton;

    @FindBy(xpath = "//h1[contains(text(),'Transfer Complete')]")
    private WebElement transferCompleteHeader;

    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    public void transferFunds(String amount) {
        click(transferFundsMenu);
        type(amountInput, amount);
        click(transferButton);
    }

    public boolean isTransferComplete() {
        return isDisplayed(transferCompleteHeader);
    }
}
