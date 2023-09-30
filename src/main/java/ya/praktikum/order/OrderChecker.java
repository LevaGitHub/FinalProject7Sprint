package ya.praktikum.order;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static ya.praktikum.Constants.STATUS_CODE_200;
import static ya.praktikum.Constants.STATUS_CODE_201;

public class OrderChecker {

    public void creationSuccess(ValidatableResponse response) {
        response.assertThat()
                .statusCode(STATUS_CODE_201)
                .body("track", notNullValue());

    }

    public void getOrderListSuccess(ValidatableResponse response) {
        response.assertThat()
                .body("orders", notNullValue())
                .statusCode(STATUS_CODE_200);;

    }

}
