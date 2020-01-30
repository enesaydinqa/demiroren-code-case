package com.pages;

import com.objects.BillingInformation;
import com.objects.Delivery;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
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

    @FindBy(id = "first-name")
    public WebElement billingFirstNameInput;

    @FindBy(id = "last-name")
    public WebElement billingLastNameInput;

    @FindBy(css = ".address-form #city + div button")
    public WebElement billingCitySelect;

    @FindBy(css = ".address-form #town + div button")
    public WebElement billingTownSelect;

    @FindBy(css = ".address-form #district + div button")
    public WebElement billingDistrictSelect;

    @FindBy(css = ".address-form #city + div .input")
    public WebElement billingCitySearchInput;

    @FindBy(css = ".address-form #town + div .input")
    public WebElement billingTownSearchInput;

    @FindBy(css = ".address-form #district + div .input")
    public WebElement billingDistrictSearchInput;

    @FindBy(css = ".address-form #city + div li:nth-child(2)")
    public List<WebElement> billingCitySearchResultItems;

    @FindBy(css = ".address-form #town + div li:nth-child(2)")
    public List<WebElement> billingTownSearchResultItems;

    @FindBy(css = ".address-form #district + div li:nth-child(2)")
    public List<WebElement> billingDistrictSearchResultItems;

    @FindBy(id = "address")
    public WebElement billingAddressTextArea;

    @FindBy(css = ".address-form #address-name")
    public WebElement billingAddressNameInput;

    @FindBy(css = ".address-form #phone")
    public WebElement billingPhoneInput;

    @FindBy(css = ".billing-type label")
    public List<WebElement> billingTypeCheckboxes;

    @FindBy(css = ".address-form .btn-primary")
    public WebElement addressSaveButton;

    @FindBy(css = "#short-summary button")
    public WebElement orderContinueButton;

    @FindBy(css = ".easy-point-proceed-info p")
    public WebElement easyPointProceedInfoPopupText;

    @FindBy(css = ".easy-point-proceed-info button")
    public WebElement easyPointProceedInfoPopupContinueButton;

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

    public DeliveryPage enterAddBillingAddress(BillingInformation information) throws Exception
    {
        browser.scrollToElement(addBillingAddressButton);
        browser.click(addBillingAddressButton);
        browser.sendKeys(billingFirstNameInput, information.getFirstName());
        browser.sendKeys(billingLastNameInput, information.getLastName());
        browser.click(billingCitySelect);
        browser.sendKeys(billingCitySearchInput, information.getCity());
        browser.sleep(1);
        billingCitySearchResultItems.get(0).click();
        browser.click(billingTownSelect);
        browser.sendKeys(billingTownSearchInput, information.getTown());
        browser.sleep(1);
        billingTownSearchResultItems.get(0).click();
        browser.click(billingDistrictSelect);
        browser.sendKeys(billingDistrictSearchInput, information.getDistrict());
        browser.sleep(1);
        billingDistrictSearchResultItems.get(0).click();
        browser.sendKeys(billingAddressTextArea, information.getAddress());
        browser.sendKeys(billingAddressNameInput, information.getAddressName());
        browser.sendKeys(billingPhoneInput, information.getPhone());

        switch (information.getBillingType())
        {
            case INDIVIDUAL:
                browser.click(billingTypeCheckboxes.get(0));
                break;
            case CORPORATE:
                browser.click(billingTypeCheckboxes.get(1));
                break;

            default:
                throw new Exception("undefined billing type" + information.getBillingType());
        }

        browser.click(addressSaveButton);

        return this;
    }

    public DeliveryPage orderContinueButtonClick()
    {
        browser.click(orderContinueButton);

        return this;
    }

    public PaymentPage assertDeliveryPointAndContinueButtonClick(String deliveryPointName)
    {
        Assert.assertThat(browser.getText(easyPointProceedInfoPopupText), CoreMatchers.containsString(deliveryPointName));

        browser.click(easyPointProceedInfoPopupContinueButton);

        return new PaymentPage(driver);
    }

}
