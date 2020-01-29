package com.pages;

import com.objects.BillingInformation;
import com.objects.Delivery;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DeliveryPage extends PageObject
{
    public DeliveryPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(id = "checkout-navigation")
    public WebElement checkoutNavigation;

    @FindBy(css = ".delivery-tabs li")
    public List<WebElement> deliveryTabs;

    @FindBy(id = "ep-first-name")
    public WebElement firstNameInput;

    @FindBy(id = "ep-last-name")
    public WebElement lastNameInput;

    @FindBy(id = "easy-point-phone")
    public WebElement phoneInput;

    @FindBy(css = "#easy-point .btn-add-new")
    public WebElement addBillingAddressButton;

    @FindBy(id = "fancybox-loading")
    public WebElement fancyboxLoading;

    public DeliveryPage enterDeliveryPoint(Delivery delivery)
    {
        browser.waitElementVisible(checkoutNavigation);
        browser.click(deliveryTabs.get(1));
        browser.sleep(3);
        browser.clickByText(delivery.getDeliveryPoint());
        browser.sleep(1);
        browser.sendKeys(firstNameInput, delivery.getFirstName());
        browser.sendKeys(lastNameInput, delivery.getLastName());
        browser.sendKeys(phoneInput, String.valueOf(delivery.getPhone()));

        return this;
    }

    public DeliveryPage enterAddBillingAddress(BillingInformation information)
    {
        browser.scrollToElement(addBillingAddressButton);
        browser.click(addBillingAddressButton);
        browser.waitElementNotVisible(fancyboxLoading);

        return this;
    }

}
