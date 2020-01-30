package com.selenium;

import com.context.AbstractSeleniumTest;
import com.context.Constants;
import com.context.UrlFactory;
import com.enums.AccordionType;
import com.enums.BillingType;
import com.enums.OrderSteps;
import com.junit.annotations.WebTest;
import com.objects.BillingInformation;
import com.objects.Delivery;
import com.pages.HomePage;
import com.pages.login.LoginPage;
import com.pages.product.ChartPage;
import com.pages.product.LaptopAndNotebookPage;
import com.pages.product.ProductDetailPage;
import org.apache.commons.lang3.RandomStringUtils;
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
    private LoginPage loginPage;

    @BeforeEach
    public void before()
    {
        homePage = new HomePage(driver);
        laptopAndNotebookPage = new LaptopAndNotebookPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        chartPage = new ChartPage(driver);
        loginPage = new LoginPage(driver);
    }

    @WebTest
    public void testOrderCompletion() throws Exception
    {
        browser.navigateToURL(UrlFactory.MAIN_URL);
        homePage.selectLaptopMenuOption();

        String selectProductName = laptopAndNotebookPage.getProductName(5);
        String selectProductPrice = laptopAndNotebookPage.getProductPrice(5);
        logger.info("selected product name : {} , price : {}", selectProductName, selectProductPrice);

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

        String email = RandomStringUtils.randomAlphanumeric(10).concat("@gmail.com");

        loginPage
                .continueWithoutMembership(email)
                .enterDeliveryPoint(createDeliveryContext())
                .enterAddBillingAddress(createBillingContext())
                .orderContinueButtonClick()
                .assertDeliveryPointAndContinueButtonClick(Constants.DeliveryPoints.ISTANBUL_KANYON_EASY_POINT)
                .selectAccordion(AccordionType.REMIT)
                .continueButtonClick()
                .selectIBANEFTOption()
                .assertCurrentPage(OrderSteps.ORDER_SUMMARY)
                .assertProductName(openedProductName)
                .assertProductPrice(selectProductPrice);
    }

    private Delivery createDeliveryContext()
    {
        Delivery delivery = new Delivery();
        delivery.setDeliveryPoint(Constants.DeliveryPoints.ISTANBUL_KANYON_EASY_POINT);
        delivery.setFirstName(RandomStringUtils.randomAlphabetic(6));
        delivery.setLastName(RandomStringUtils.randomAlphabetic(4));
        delivery.setPhone("552" + RandomStringUtils.randomNumeric(7));

        return delivery;
    }

    private BillingInformation createBillingContext()
    {
        BillingInformation billingInformation = new BillingInformation();
        billingInformation.setFirstName(RandomStringUtils.randomAlphabetic(6));
        billingInformation.setLastName(RandomStringUtils.randomAlphabetic(4));
        billingInformation.setCountry("Türkiye");
        billingInformation.setCity("İstanbul");
        billingInformation.setTown("ADALAR");
        billingInformation.setDistrict("ATATÜRK MAHALLESİ");
        billingInformation.setAddress(RandomStringUtils.randomAlphanumeric(30));
        billingInformation.setAddressName(RandomStringUtils.randomAlphabetic(5));
        billingInformation.setPhone("552" + RandomStringUtils.randomNumeric(7));
        billingInformation.setBillingType(BillingType.INDIVIDUAL);

        return billingInformation;
    }
}
