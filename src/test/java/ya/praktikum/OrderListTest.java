package ya.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ya.praktikum.model.Order;
import ya.praktikum.order.OrderChecker;
import ya.praktikum.order.OrderHelper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {
    private final OrderHelper orderHelper = new OrderHelper();
    private final OrderChecker orderChecker = new OrderChecker();

    @Test
    @DisplayName("Get order list")
    @Description("Проверка запроса на получение списка заказов")
    public void getOrderList(){
        ValidatableResponse getOrderListResponse = orderHelper.getOrderList();
        orderChecker.getOrderListSuccess(getOrderListResponse);
    }
}
