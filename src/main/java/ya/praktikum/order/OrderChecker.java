package ya.praktikum.order;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static ya.praktikum.Constants.STATUS_CODE_200;
import static ya.praktikum.Constants.STATUS_CODE_201;

public class OrderChecker {

    @Step("Проверка ответа на запрос создания заказа")
    public void creationSuccess(ValidatableResponse response) {
        response.assertThat()
                .statusCode(STATUS_CODE_201)
                .body("track", notNullValue());

    }

    @Step("Проверка ответа на запрос получения списка заказов")
    public void getOrderListSuccess(ValidatableResponse response) {
        response.assertThat()
                .body("orders", notNullValue())
                .statusCode(STATUS_CODE_200);;

    }

}
