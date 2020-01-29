package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends PageObject
{
    public HeaderPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(id = "shoppingCart")
    private WebElement shoppingCartButton;

    public void clickShoppingCartButton()
    {
        browser.waitElementNotVisible(notification);
        browser.click(shoppingCartButton);
    }
}
