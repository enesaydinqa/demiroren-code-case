package com.junit.extensions;

import com.junit.context.DriverManager;
import com.junit.context.WebTestFactory;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class WebExtension extends DriverManager implements BeforeEachCallback, AfterEachCallback
{
    private static final Logger logger = LoggerFactory.getLogger(WebExtension.class);

    private ChromeOptions chromeOptions;

    public void beforeEach(ExtensionContext extensionContext) throws Exception
    {
        DriverManager driverManager = WebTestFactory.getManager();

        driver = driverManager.getDriver();
    }

    public void afterEach(ExtensionContext extensionContext)
    {
        if (driver != null)
        {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

    @Override
    public void createDriver()
    {
        chromeOptions = chromeOptions();

        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("/etc/chromedriver"))
                .build();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            driver = new ChromeDriver(chromeDriverService, chromeOptions);
            return "Driver created";
        });

        try
        {
            logger.info(future.get(1, TimeUnit.MINUTES));
        }
        catch (TimeoutException e)
        {
            logger.error("WebDriver does not created within 1 Minute");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        executor.shutdownNow();
    }

    private ChromeOptions chromeOptions()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");

        return chromeOptions;
    }
}
