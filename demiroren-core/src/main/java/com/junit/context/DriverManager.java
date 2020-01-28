package com.junit.context;

import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class DriverManager
{
    protected static RemoteWebDriver driver;

    public RemoteWebDriver getDriver()
    {
        if (driver == null)
        {
            createDriver();
        }
        return driver;
    }

    public void createDriver()
    {
    }
}
