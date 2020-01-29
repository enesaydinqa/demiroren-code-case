package com.pages;

import com.objects.BillingInformationContext;
import com.objects.DeliveryContext;
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

    public DeliveryPage enterDeliveryPoint(DeliveryContext context)
    {
        browser.waitElementVisible(checkoutNavigation);
        browser.click(deliveryTabs.get(1));
        browser.sleep(3);
        browser.clickByText(context.getDeliveryPoint());
        browser.sleep(1);
        browser.sendKeys(firstNameInput, context.getFirstName());
        browser.sendKeys(lastNameInput, context.getLastName());
        browser.sendKeys(phoneInput, String.valueOf(context.getPhone()));

        return this;
    }

    public DeliveryPage enterAddBillingAddress(BillingInformationContext context)
    {
        browser.scrollToElement(addBillingAddressButton);
        browser.click(addBillingAddressButton);
        browser.waitElementNotVisible(fancyboxLoading);

        return this;
    }

}
