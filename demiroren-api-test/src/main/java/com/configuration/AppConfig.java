package com.configuration;

import com.utilities.RestAssuredExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;

@Order(0)
@Configuration
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class AppConfig
{
    @Autowired
    public RestAssuredExtension restAssuredExtension;

}
