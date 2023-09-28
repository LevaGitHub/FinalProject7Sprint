package ya.praktikum.courier;

import io.restassured.response.ValidatableResponse;
import ya.praktikum.BaseRequest;
import ya.praktikum.model.Courier;

import java.util.UUID;

public class CourierHelper extends BaseRequest {
    protected final String CREATE_COURIER_METHOD_PATH = "/courier";




    public ValidatableResponse create(Courier courier) {
        return sendBaseRequest()
                .body(courier)
                .when()
                .post(CREATE_COURIER_METHOD_PATH)
                .then().log().all();
    }
}