package com.pages.login;

import com.pages.DeliveryPage;
import com.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends PageObject
{
    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(className = "box-header-title")
    private WebElement memberLoginTitle;

    @FindBy(css = ".login-selections div label")
    private List<WebElement> loginSelections;

    @FindBy(id = "lazy-email")
    private WebElement emailInput;

    @FindBy(css = "#form-lazy-login .btn-login-submit")
    private WebElement loginSubmitButton;

    public DeliveryPage continueWithoutMembership(String email)
    {
        browser.waitElementVisible(memberLoginTitle);
        browser.click(loginSelections.get(2));
        browser.sendKeys(emailInput, email);
        browser.click(loginSubmitButton);

        return new DeliveryPage(driver);
    }

}
