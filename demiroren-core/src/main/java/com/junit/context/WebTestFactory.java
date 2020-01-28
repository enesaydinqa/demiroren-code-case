package com.junit.context;

import com.junit.extensions.WebExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebTestFactory
{
    private static final Logger logger = LoggerFactory.getLogger(WebTestFactory.class);

    public static DriverManager getManager() throws Exception
    {
        DriverManager driverManager;

        BrowserType browserType = BrowserType.valueOf(System.getProperty("browser.type"));

        logger.info("BrowserType -> {}", browserType);

        switch (browserType)
        {
            case CHROME:
                driverManager = new WebExtension();
                break;

            default:
                throw new Exception("undefined browser type : " + browserType);
        }

        return driverManager;
    }
}
