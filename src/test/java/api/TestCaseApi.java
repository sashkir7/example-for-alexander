package api;

import models.testCase.InputTestCaseDto;
import models.testCase.TestCaseDto;

import static io.restassured.RestAssured.given;

public class TestCaseApi extends BaseApi {

    public static TestCaseDto createTestCase(String projectId, InputTestCaseDto inputTestCase) {
        return given().spec(getBaseRequestAuthSpec())
                    .pathParam("projectId", projectId)
                    .body(inputTestCase)
                .when()
                    .post("api/rs/testcasetree/leaf?projectId={projectId}")
                .then().spec(getBaseResponseSpec())
                    .statusCode(200)
                    .extract().as(TestCaseDto.class);
    }

}
