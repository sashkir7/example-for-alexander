package api;

import config.UserProperties;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseApi {

    public final static String ALLURE_TESTOPS_SESSION = "ALLURE_TESTOPS_SESSION";

    public static String getXsrfToken() {
        return given()
                    .formParam("grant_type", "apitoken")
                    .formParam("scope", "openid")
                    .formParam("token", UserProperties.TOKEN)
                .when()
                    .post("/api/uaa/oauth/token")
                .then()
                    .statusCode(200)
                    .extract()
                    .path("jti");
    }

    public static String getAuthorizationCookie() {
        String xsrfToken = getXsrfToken();
        return given()
                    .header("X-XSRF-TOKEN", xsrfToken)
                    .header("Cookie", "XSRF-TOKEN=" + xsrfToken)
                    .formParam("username", UserProperties.USERNAME)
                    .formParam("password", UserProperties.PASSWORD)
                .when()
                    .post("/api/login/system")
                .then()
                    .statusCode(200)
                    .extract()
                    .response()
                    .getCookie(ALLURE_TESTOPS_SESSION);
    }

}
