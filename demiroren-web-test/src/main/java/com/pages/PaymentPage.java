package com.pages;

import com.enums.AccordionType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PaymentPage extends PageObject
{
    public PaymentPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = ".accordions > div span")
    public List<WebElement> accordionTypes;

    @FindBy(css = ".money-order-banks > li")
    public List<WebElement> orderBanks;

    @FindBy(css = "#short-summary button")
    public WebElement continueButton;

    @FindBy(css = ".iban-eft span:nth-child(2)")
    public WebElement ibanEFTOption;

    @FindBy(xpath = "//button[text()='Devam Et']")
    public WebElement eftInfoContinueButton;

    public PaymentPage selectAccordion(final AccordionType accordionType)
    {
        browser.sleep(5);

        accordionTypes.forEach(type ->
        {
            if (browser.getText(type).equals(accordionType.accordionType)) browser.click(type);
        });

        browser.sleep(3);
        browser.click(orderBanks.get(0));

        return this;
    }

    public PaymentPage continueButtonClick()
    {
        browser.click(continueButton);

        return this;
    }

    public OrderSummaryPage selectIBANEFTOption()
    {
        browser.click(ibanEFTOption);
        browser.click(eftInfoContinueButton);

        return new OrderSummaryPage(driver);
    }


}
