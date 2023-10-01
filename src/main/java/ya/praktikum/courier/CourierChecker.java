package ya.praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static ya.praktikum.Constants.*;

public class CourierChecker {

    public static final String FAIL_RESPONSE_INSUFFICIENT_DATA_CREATE_COURIER = "Недостаточно данных для создания учетной записи";
    public static final String FAIL_RESPONSE_LOGIN_EXISTS = "Этот логин уже используется";
    public static final String FAIL_RESPONSE_INSUFFICIENT_DATA_LOGIN = "Недостаточно данных для входа";
    public static final String FAIL_RESPONSE_NOT_FOUND_DATA_LOGIN = "Учетная запись не найдена";


    @Step("Проверка ответа на запрос создания курьера")
    public void creationSuccess(ValidatableResponse response) {
        response.assertThat()
                .statusCode(STATUS_CODE_201)
                .body("ok", is(true));
    }

    @Step("Проверка ошибки в ответе на запрос создания курьера (400)")
    public void creationFailedBadRequest(ValidatableResponse response) {
        response.assertThat()
                .statusCode(STATUS_CODE_400)
                .body("message", is(FAIL_RESPONSE_INSUFFICIENT_DATA_CREATE_COURIER));
    }

    @Step("Проверка ошибки в ответе на запрос создания курьера (409)")
    public void creationFailedConflict(ValidatableResponse response) {
        response.assertThat()
                .statusCode(STATUS_CODE_409)
                .body("message", is(FAIL_RESPONSE_LOGIN_EXISTS));
    }

    @Step("Проверка текста сообщения в ответе")
    public void checkMessage(ValidatableResponse response, String message) {
        response.assertThat()
                .body("message", is(message));
    }

    @Step("Проверка ответа на запрос логина курьера")
    public void loginSuccess(ValidatableResponse response) {
        response.assertThat()
                .statusCode(STATUS_CODE_200)
                .body("id", greaterThan(0));
    }

    @Step("Проверка ошибки на запрос логина курьера (400)")
    public void loginFailBadRequest(ValidatableResponse response) {
        response.assertThat()
                .statusCode(STATUS_CODE_400)
                .body("message", is(FAIL_RESPONSE_INSUFFICIENT_DATA_LOGIN));
    }

    @Step("Проверка ошибки на запрос логина курьера (404)")
    public void loginFailDataNotFound(ValidatableResponse response) {
        response.assertThat()
                .statusCode(STATUS_CODE_404)
                .body("message", is(FAIL_RESPONSE_NOT_FOUND_DATA_LOGIN));
    }

}
