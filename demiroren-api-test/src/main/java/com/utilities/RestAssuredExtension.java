package com.utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

@Component
public class RestAssuredExtension
{
    public RequestSpecification requestSpecification;

    public RestAssuredExtension()
    {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("https://restcountries.eu");
        builder.setContentType(ContentType.JSON);
        RequestSpecification requestSpec = builder.build();
        requestSpecification = RestAssured.given().spec(requestSpec);
    }

    public RequestSpecification getRequestSpecification()
    {
        return requestSpecification;
    }
}
