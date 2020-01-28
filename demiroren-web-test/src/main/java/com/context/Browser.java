package com.context;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Browser implements Actions
{
    private static final Logger logger = LoggerFactory.getLogger(Browser.class);

    private WebDriver driver;

    public Browser(WebDriver driver)
    {
        this.driver = driver;
    }

    @Override
    public String getCurrentURL()
    {
        return driver.getCurrentUrl();
    }

    @Override
    public void navigateToURL(UrlFactory url)
    {
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.MINUTES);
        driver.navigate().to(url.pageUrl);
        logger.info("Redirect : {}", url.pageUrl);
    }

    @Override
    public void click(WebElement element)
    {
        waitElementToBeClickable(element);
        element.click();
    }

    @Override
    public void mouseOver(WebElement element)
    {
        waitElementVisible(element);
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.moveToElement(element).perform();
    }

    @Override
    public void selectOptionIndex(WebElement element, int index)
    {
        new Select(element).selectByIndex(index);
    }

    @Override
    public void selectOptionValue(WebElement element, String itemValue)
    {
        new Select(element).selectByValue(itemValue);
    }

    @Override
    public void selectOptionVisibleText(WebElement element, String visibleText)
    {
        new Select(element).selectByVisibleText(visibleText);
    }

    @Override
    public void sendKeys(WebElement element, CharSequence text)
    {
        element.sendKeys(text);
    }

    @Override
    public boolean isDisplayed(WebElement element)
    {
        boolean isDisplayed = false;

        try
        {
            isDisplayed = element.isDisplayed() ? true : false;
        }
        catch (Exception ex)
        {

        }

        return isDisplayed;
    }

    @Override
    public boolean isAttributePresent(WebElement element, String attribute)
    {
        Boolean result = false;

        try
        {
            String value = element.getAttribute(attribute);
            if (value != null)
            {
                result = true;
            }
        }
        catch (Exception e)
        {
        }

        return result;
    }

    @Override
    public void waitElementToBeClickable(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Override
    public void waitElementVisible(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public void waitElementNotVisible(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    @Override
    public void clearInput(WebElement element)
    {
        element.clear();
    }

    @Override
    public void clearMultipleSelectedOption(WebElement element)
    {
        new Select(element).deselectAll();
    }

    @Override
    public String getText(WebElement element)
    {
        waitElementVisible(element);
        return element.getText();
    }

    @Override
    public String getSelectedOptionText(WebElement element)
    {
        Select dropdown = new Select(element);
        return dropdown.getFirstSelectedOption().getText();
    }

    @Override
    public String getAttribute(WebElement element, String attributeName)
    {
        return element.getAttribute(attributeName);
    }

    @Override
    public String selectedOptionGetText(WebElement element)
    {
        return new Select(element).getFirstSelectedOption().getText();
    }

    @Override
    public String selectedOptionGetValue(WebElement element)
    {
        return new Select(element).getFirstSelectedOption().getAttribute("value");
    }

    @Override
    public void wait(int seconds)
    {
        try
        {
            Thread.sleep(seconds * 1000);
        }
        catch (Exception ex)
        {
        }

    }

    @Override
    public void pageLoad()
    {
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @Override
    public void implicitlyWait()
    {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Override
    public void assertEquals(Object actual, Object expected)
    {
        Assert.assertEquals(actual, expected);
    }

    @Override
    public void checkBoxChecked(WebElement element)
    {
        element.isSelected();
    }

    @Override
    public void pageRefresh()
    {
        driver.navigate().refresh();
    }

    @Override
    public void keysENTER(WebElement element)
    {
        element.sendKeys(Keys.ENTER);
    }

    @Override
    public void switchWindowTab(int tab)
    {
        ArrayList<String> TabSwitch = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(TabSwitch.get(tab));
    }

    @Override
    public void switchParentFrame()
    {
        driver.switchTo().parentFrame();
    }

    @Override
    public void switchFrame(WebElement element)
    {
        driver.switchTo().frame(element);
    }

    @Override
    public String getWindowHandle()
    {
        return driver.getWindowHandle();
    }

    @Override
    public void deleteCookie()
    {
        driver.manage().deleteAllCookies();
    }

}
