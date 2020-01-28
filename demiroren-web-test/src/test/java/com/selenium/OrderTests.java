package com.selenium;

import com.context.AbstractSeleniumTest;
import com.context.UrlFactory;
import com.junit.annotations.WebTest;
import com.pages.HomePage;
import com.pages.product.ChartPage;
import com.pages.product.LaptopAndNotebookPage;
import com.pages.product.ProductDetailPage;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderTests extends AbstractSeleniumTest
{
    private static final Logger logger = LoggerFactory.getLogger(OrderTests.class);

    private HomePage homePage;
    private LaptopAndNotebookPage laptopAndNotebookPage;
    private ProductDetailPage productDetailPage;
    private ChartPage chartPage;

    @BeforeEach
    public void before()
    {
        homePage = new HomePage(driver);
        laptopAndNotebookPage = new LaptopAndNotebookPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        chartPage = new ChartPage(driver);
    }

    @WebTest
    public void testOrderCompletion()
    {
        browser.navigateToURL(UrlFactory.MAIN_URL);

        homePage.selectLaptopMenuOption();

        String selectProductName = laptopAndNotebookPage.getProductName(5);

        logger.info("selected product name : {}", selectProductName);

        laptopAndNotebookPage.selectProduct(5);

        String openedProductName = productDetailPage.getProductName();

        logger.info("opened product name : {}", openedProductName);

        Assert.assertEquals("opened product is wrong !", selectProductName, openedProductName);

        productDetailPage
                .addToChartButton()
                .clickShoppingCartButton();

        String chartProductName = chartPage.getProductName();

        logger.info("chart product name : {}", chartProductName);

        Assert.assertEquals("chart added product is wrong !", openedProductName, chartProductName);

        chartPage.clickCompleteShoppingButton();
    }
}
