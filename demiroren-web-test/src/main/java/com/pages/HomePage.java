package com.pages;

import com.pages.product.LaptopAndNotebookPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject
{
    private WebDriver driver;

    public HomePage(WebDriver driver)
    {
        super(driver);

        this.driver = driver;
    }

    @FindBy(id = "elektronik")
    private WebElement electronicMenu;

    @FindBy(css = "[data-title='Bilgisayar/Tablet']")
    private WebElement computerTabletItem;

    @FindBy(css = "[data-title='Dizüstü Bilgisayar']")
    private WebElement laptopOption;

    public LaptopAndNotebookPage selectLaptopMenuOption()
    {
        browser.mouseOver(electronicMenu);
        browser.mouseOver(computerTabletItem);
        browser.click(laptopOption);

        return new LaptopAndNotebookPage(driver);
    }
}
