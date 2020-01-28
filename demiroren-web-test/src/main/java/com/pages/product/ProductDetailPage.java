package com.pages.product;

import com.pages.HeaderPage;
import com.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends PageObject
{
    private WebDriver driver;

    public ProductDetailPage(WebDriver driver)
    {
        super(driver);

        this.driver = driver;
    }

    @FindBy(id = "product-name")
    private WebElement productName;

    @FindBy(id = "addToCart")
    private WebElement addToCartButton;


    public String getProductName()
    {
        return browser.getText(productName);
    }

    public HeaderPage addToChartButton()
    {
        browser.click(addToCartButton);

        return new HeaderPage(driver);
    }

}

