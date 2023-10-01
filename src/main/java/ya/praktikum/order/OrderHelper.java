package ya.praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ya.praktikum.BaseRequest;
import ya.praktikum.model.Order;

import static io.restassured.RestAssured.given;

public class OrderHelper extends BaseRequest {

    protected final String CREATE_ORDER_METHOD_PATH = "/orders";
    protected final String CANCEL_ORDER_METHOD_PATH = "/orders/cancel";

    @Step("Создание заказа")
    public ValidatableResponse create(Order order) {
        System.out.println(order);
        return sendBaseRequest()
                .body(order)
                .when()
                .post(CREATE_ORDER_METHOD_PATH)
                .then().log().all();
    }

    @Step("Получение номера заказа")
    public int getOrderId(ValidatableResponse response) {
        return response
                .extract()
                .path("track");
    }

    @Step("Отправка запроса на отмену заказа")
    public ValidatableResponse cancel(int orderId){
        String json = String.format("{\"track\": %d}", orderId);
        return sendBaseRequest()
                .body(json)
                .when()
                .put(CANCEL_ORDER_METHOD_PATH)
                .then().log().all();
    }

    @Step("Отправка запроса на получение списка заказов")
    public ValidatableResponse getOrderList() {
        return sendBaseRequest()
                .get(CREATE_ORDER_METHOD_PATH)
                .then().log().all();
    }
}
