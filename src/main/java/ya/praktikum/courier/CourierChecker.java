package ya.praktikum.courier;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static ya.praktikum.Constants.*;

public class CourierChecker {

    public static final String FAIL_RESPONSE_INSUFFICIENT_DATA = "Недостаточно данных для создания учетной записи";
    public static final String FAIL_RESPONSE_LOGIN_EXISTS = "Этот логин уже используется";

    public int extractCourierId(ValidatableResponse response) {
        return response
                .extract()
                .path("id");
    }

    public void creationSuccess(ValidatableResponse response) {
        response.assertThat()
                .statusCode(STATUS_CODE_201)
                .body("ok", is(true));
    }

    public void creationFailedBadRequest(ValidatableResponse response) {
        response.assertThat()
                .statusCode(STATUS_CODE_400)
                .body("message", is(FAIL_RESPONSE_INSUFFICIENT_DATA));
    }

    public void creationFailedConflict(ValidatableResponse response) {
        response.assertThat()
                .statusCode(STATUS_CODE_409)
                .body("message", is(FAIL_RESPONSE_LOGIN_EXISTS));
    }


    public void checkMessage(ValidatableResponse response, String message) {
        response.assertThat()
                .body("message", is(message));
    }

}
