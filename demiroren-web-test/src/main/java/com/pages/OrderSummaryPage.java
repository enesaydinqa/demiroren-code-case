package com.pages;

import com.enums.OrderSteps;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderSummaryPage extends PaymentPage
{
    private static final Logger logger = LoggerFactory.getLogger(OrderSummaryPage.class);

    public OrderSummaryPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = "li.current span.nav-name")
    private WebElement currentNavSummary;

    @FindBy(css = ".col-product-name")
    private WebElement productNameText;

    @FindBy(css = "#item-prices .list-item:nth-child(1) strong")
    private WebElement totalPriceText;

    public OrderSummaryPage assertCurrentPage(OrderSteps orderSteps)
    {
        browser.waitForPageLoad();
        browser.sleep(3);

        String currentStep = browser.getText(currentNavSummary);
        logger.info("current step : {}", currentStep);

        Assert.assertEquals("order summary page is wrong !", orderSteps.orderSteps, currentStep);

        return this;
    }

    public OrderSummaryPage assertProductName(String productName)
    {
        String name = browser.getText(productNameText);

        logger.info("product name : {}", name);
        Assert.assertEquals("product name is wrong !", productName, name);

        return this;
    }

    public OrderSummaryPage assertProductPrice(String productPrice)
    {
        String price = browser.getText(totalPriceText);

        logger.info("product price : {}", price);
        Assert.assertEquals("product price is wrong !", productPrice, price);

        return this;
    }
}
