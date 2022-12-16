package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseApi {

    protected static RequestSpecification getBaseRequestSpec() {
        return new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setContentType(ContentType.JSON)
                .build();
    }

    protected static RequestSpecification getBaseRequestAuthSpec() {
        String xsrfToken = AuthApi.getXsrfToken();
        String authorizationCookie = AuthApi.getAuthorizationCookie();
        return getBaseRequestSpec()
                .header("X-XSRF-TOKEN", xsrfToken)
                .cookie("XSRF-TOKEN", xsrfToken)
                .cookie(AuthApi.ALLURE_TESTOPS_SESSION, authorizationCookie);
    }

    protected static ResponseSpecification getBaseResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

}
