package tests;

import api.TestCaseApi;
import models.testCase.InputTestCaseDto;
import models.testCase.TestCaseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static api.AuthApi.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class TestCaseTest extends BaseTest {

    private final String projectId = "1740";

    @Test
    @DisplayName("Создание тест-кейса")
    void createTestCase() {
        InputTestCaseDto inputTestCase = step("Создаем модель тест-кейса", () ->
                InputTestCaseDto.builder()
                        .name(faker.book().title())
                        .build());

        TestCaseDto testCase = step("Создаем тест-кейс через API", () ->
                TestCaseApi.createTestCase(projectId, inputTestCase));

        step("Авторизуемся в системе", () -> {
            open("/favicon.ico");
            getWebDriver().manage().addCookie(new Cookie(ALLURE_TESTOPS_SESSION, getAuthorizationCookie()));
        });

        step("Проверяем, что на UI был создан тест-кейс", () -> {
            open("/project/" + projectId + "/test-cases/" + testCase.getId());
            $(".TestCaseLayout__id").shouldHave(text(testCase.getId().toString()));
            $(".TestCaseLayout__name").shouldHave(text(testCase.getName()));
        });
    }

}
