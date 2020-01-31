package restassured;

import com.configuration.AppConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit.annotations.ApiTest;
import com.objects.Country;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.stream.IntStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RestCountriesTest extends AppConfig
{
    private static final Logger logger = LoggerFactory.getLogger(RestCountriesTest.class);

    @ApiTest
    public void testCountryDetail() throws IOException
    {
        RequestSpecification request = restAssuredExtension
                .getRequestSpecification()
                .given()
                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()))
                .param("fullText", true);

        ExtractableResponse<Response> response = request
                .when()
                .get("/rest/v2/name/turkiye")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract();

        logger.info("response body -> {}", response.body().asString());

        ObjectMapper mapper = new ObjectMapper();

        Country[] countries = mapper.readValue(response.body().asString(), Country[].class);

        Assertions.assertEquals("Turkey", countries[0].getName());

        String[] expectedAltSpellings = {"TR", "Turkiye", "Republic of Turkey", "Türkiye Cumhuriyeti"};

        IntStream.range(0, expectedAltSpellings.length).forEach(i -> Assertions.assertEquals(expectedAltSpellings[i], countries[0].getAltSpellings()[i]));

        Assertions.assertEquals("TRY", countries[0].getCurrencies()[0].getCode());
        Assertions.assertEquals("Turkish lira", countries[0].getCurrencies()[0].getName());
        Assertions.assertNull(countries[0].getCurrencies()[0].getSymbol());
        Assertions.assertEquals("783562.0", countries[0].getArea());
    }
}
