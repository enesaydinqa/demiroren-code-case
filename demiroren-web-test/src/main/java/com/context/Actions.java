package com.context;

import org.openqa.selenium.WebElement;

public interface Actions
{
    String getCurrentURL();

    void navigateToURL(UrlFactory url);

    void click(WebElement element) throws InterruptedException;

    void mouseOver(WebElement element);

    void sendKeys(WebElement element, CharSequence text);

    void waitElementToBeClickable(WebElement element);

    void waitElementVisible(WebElement element);

    void waitElementNotVisible(WebElement element);

    String getText(WebElement element);

    void scrollToElement(WebElement element);

    void clickByText(String text);

    void waitForPageLoad();

}
