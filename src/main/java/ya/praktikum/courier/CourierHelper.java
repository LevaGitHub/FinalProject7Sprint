package ya.praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ya.praktikum.BaseRequest;
import ya.praktikum.model.Courier;
import ya.praktikum.model.User;

public class CourierHelper extends BaseRequest {
    protected final String CREATE_COURIER_METHOD_PATH = "/courier";
    protected final String LOGIN_COURIER_METHOD_PATH = "/courier/login";

    @Step("Отправка запроса на создание курьера")
    public ValidatableResponse create(Courier courier) {
        return sendBaseRequest()
                .body(courier)
                .when()
                .post(CREATE_COURIER_METHOD_PATH)
                .then().log().all();
    }

    @Step("Отправка запроса на авторизацию курьера")
    public ValidatableResponse login(User user) {
        return sendBaseRequest()
                .body(user)
                .when()
                .post(LOGIN_COURIER_METHOD_PATH)
                .then().log().all();
    }

    @Step("Отправка запроса на удаление курьера")
    public ValidatableResponse delete(int courierId) {
        String json = String.format("{\"id\": \"%d\"}", courierId);
        return sendBaseRequest()
                .body(json)
                .when()
                .delete(CREATE_COURIER_METHOD_PATH + "/" + courierId)
                .then().log().all();
    }

    public int getCourierId(Courier courierData){
        User userData = User.from(courierData);
        ValidatableResponse loginResponse = login(userData);
        return extractCourierId(loginResponse);

    };
    @Step("Получение id курьера")
    public int extractCourierId(ValidatableResponse response) {
        return response
                .extract()
                .path("id");
    }
}