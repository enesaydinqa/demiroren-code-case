package com.pages;

import com.enums.OrderSteps;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderSummaryPage extends PaymentPage
{
    public OrderSummaryPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "li.current span.nav-name")
    public WebElement currentNavSummary;

    @FindBy(xpath = ".col-product-name")
    public WebElement productNameText;

    @FindBy(xpath = "#short-summary .total-price span")
    public WebElement totalPriceText;

    public OrderSummaryPage assertCurrentPage(OrderSteps orderSteps)
    {
        Assert.assertEquals("order summary page is wrong !", orderSteps.orderSteps, browser.getText(currentNavSummary));

        return this;
    }

    public OrderSummaryPage assertProductName(String productName)
    {
        Assert.assertEquals("product name is wrong !", productName, browser.getText(productNameText));

        return this;
    }

    public OrderSummaryPage assertProductPrice(String productPrice)
    {
        Assert.assertEquals("product price is wrong !", productPrice, browser.getText(totalPriceText));

        return this;
    }
}
