package ya.praktikum;

import io.restassured.RestAssured;
import org.junit.Test;
import ya.praktikum.courier.CourierGenerator;
import ya.praktikum.courier.CourierHelper;
import ya.praktikum.model.Courier;

import static io.restassured.RestAssured.given;

public class CourierCreateTest {

    CourierHelper courierHelper = new CourierHelper();
    CourierGenerator courierGenerator = new CourierGenerator();
    @Test
    public void getMyInfoStatusCodeMesto() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
        String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGQyOThjMTQzNDI0NDAwM2QyNzdjZWQiL" +
                "CJpYXQiOjE2OTU0NTI3MDYsImV4cCI6MTY5NjA1NzUwNn0.tflNxWBsSaxIfxffc8H_8kVMjzRcpo6x_sjVEYJHRD8";
        given().log().all()
                .auth().oauth2(bearerToken)
                .get("/api/users/me")
                .then().statusCode(200);
    }



    @Test
    public void createCourier201(){

//        String method = "/api/v1/courier";
//        String uri = "https://qa-scooter.praktikum-services.ru/";
//        String json = "{\n" +
//                "    \"login\": \"java_autotest_login_79d6ac36-15fe-406d-b61a-65092b2ca4c3\",\n" +
//                "    \"password\": \"java_autotest_password_79d6ac36-15fe-406d-b61a-65092b2ca4c3\",\n" +
//                "    \"firstName\": \"java_autotest_first_name_79d6ac36-15fe-406d-b61a-65092b2ca4c3\"\n" +
//                "}";
        //        RestAssured.baseURI = uri;
        Courier courierData = courierGenerator.getRandom();
        courierHelper.create(courierData).statusCode(201);
//        given().log().all()
//                .contentType(ContentType.JSON)
//                .body(courierData)
//                .post(method)
//                .then().log().all()
//                .statusCode(201);
    }

}


