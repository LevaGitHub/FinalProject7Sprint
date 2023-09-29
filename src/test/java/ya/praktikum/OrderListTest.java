package ya.praktikum;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {

    public void getOrderList(){
        String method = "/api/v1/orders";
        String uri = "https://qa-scooter.praktikum-services.ru/";
        RestAssured.baseURI = uri;
        given().log().all()
                .get(method)
                .then().log().all()
                .assertThat().body("orders", notNullValue())
                .statusCode(200);
    }
}
