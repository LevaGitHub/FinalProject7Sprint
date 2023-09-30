package ya.praktikum;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ya.praktikum.courier.CourierGenerator;
import ya.praktikum.model.Courier;
import ya.praktikum.model.Order;
import ya.praktikum.model.User;
import ya.praktikum.order.OrderChecker;
import ya.praktikum.order.OrderGenerator;
import ya.praktikum.order.OrderHelper;

import java.util.List;

import static ya.praktikum.Constants.BLACK;
import static ya.praktikum.Constants.GREY;

@RunWith(Parameterized.class)
public class OrderCreateTest {

    private List<String> colorList;
    private final OrderHelper orderHelper = new OrderHelper();
    private final OrderGenerator orderGenerator = new OrderGenerator();
    private final OrderChecker orderChecker = new OrderChecker();
    private int orderId;


    public OrderCreateTest(List<String> colorList) {
        this.colorList = colorList;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {List.of(BLACK)},
                {List.of(GREY)},
                {List.of(BLACK, GREY)},
                {List.of()},
        };
    }

    @After
    public void cleanTestData(){
        if (orderId > 0) {
            orderHelper.cancel(orderId);
        }
    }


    @Test
    public void createOrderSuccess() {
        Order orderData = orderGenerator.getRandom();
        orderData.setColor(colorList);
        ValidatableResponse createResponse = orderHelper.create(orderData);
        orderChecker.creationSuccess(createResponse);
        orderId = orderHelper.getOrderId(createResponse);
    }

}
