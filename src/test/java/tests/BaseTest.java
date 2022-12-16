package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected final Faker faker = new Faker();

    @BeforeAll
    static void beforeAll() {
        // to config
        Configuration.baseUrl = "https://allure.autotests.cloud";
        RestAssured.baseURI = "https://allure.autotests.cloud";
    }

}
