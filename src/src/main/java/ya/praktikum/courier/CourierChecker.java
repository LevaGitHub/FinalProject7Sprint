package ya.praktikum.courier;

import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.greaterThan;

public class CourierChecker {

    public int extractCourierId(ValidatableResponse response) {
        return response
                .extract()
                .path("id");

}
}
