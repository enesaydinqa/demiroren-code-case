package com.pages.product;

import com.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LaptopAndNotebookPage extends PageObject
{
    public LaptopAndNotebookPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = ".SearchListing .product-title span")
    public List<WebElement> products;

    @FindBy(css = ".SearchListing .price-value")
    public List<WebElement> productPrices;

    public String getProductName(int index)
    {
        return browser.getText(products.get(index));
    }

    public String getProductPrice(int index)
    {
        return browser.getText(productPrices.get(index));
    }

    public void selectProduct(int index)
    {
        browser.click(products.get(index));
    }
}
