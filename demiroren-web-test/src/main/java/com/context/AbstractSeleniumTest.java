package com.context;

import com.junit.context.DriverManager;
import org.junit.jupiter.api.BeforeEach;


public abstract class AbstractSeleniumTest extends DriverManager
{
    public Browser browser;

    @BeforeEach
    public void beforeEach()
    {
        browser = new Browser(driver);
    }

}
