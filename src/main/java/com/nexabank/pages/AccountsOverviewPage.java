package com.nexabank.pages;

import com.nexabank.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountsOverviewPage extends BasePage {
    @FindBy(xpath = "//h1[contains(text(),'Accounts Overview')]")
    private WebElement pageHeader;

    @FindBy(id = "accountTable")
    private WebElement accountTable;

    @FindBy(linkText = "Log Out")
    private WebElement logoutLink;

    public AccountsOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountsOverviewDisplayed() {
        return isDisplayed(pageHeader) && isDisplayed(accountTable);
    }

    public void logout() {
        click(logoutLink);
    }
}
