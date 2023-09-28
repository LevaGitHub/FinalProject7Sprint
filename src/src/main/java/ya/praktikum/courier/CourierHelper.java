package ya.praktikum.courier;

import io.restassured.response.ValidatableResponse;
import ya.praktikum.BaseRequest;
import ya.praktikum.model.Courier;
import ya.praktikum.model.User;

import java.util.UUID;

public class CourierHelper extends BaseRequest {
    protected final String CREATE_COURIER_METHOD_PATH = "/courier";
    protected final String LOGIN_COURIER_METHOD_PATH = "/courier/login";

    public ValidatableResponse create(Courier courier) {
        return sendBaseRequest()
                .body(courier)
                .when()
                .post(CREATE_COURIER_METHOD_PATH)
                .then().log().all();
    }

    public ValidatableResponse login(User user) {
        return sendBaseRequest()
                .body(user)
                .when()
                .post(LOGIN_COURIER_METHOD_PATH)
                .then().log().all();
    }

    public ValidatableResponse delete(int courierId) {
        String json = String.format("{\"id\": \"%d\"}", courierId);
        return sendBaseRequest()
                .body(json)
                .when()
                .delete(CREATE_COURIER_METHOD_PATH + "/" + courierId)
                .then().log().all();
    }
}