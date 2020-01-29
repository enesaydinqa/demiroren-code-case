package com.pages.product;

import com.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChartPage extends PageObject
{
    public ChartPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = ".product-name a")
    private WebElement productName;

    @FindBy(xpath = "//span[text()='Alışverişi Tamamla']")
    private WebElement completeShoppingButton;

    public String getProductName()
    {
        return browser.getText(productName);
    }

    public void clickCompleteShoppingButton()
    {
        browser.click(completeShoppingButton);
    }
}
