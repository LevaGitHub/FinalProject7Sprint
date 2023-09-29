package ya.praktikum;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ya.praktikum.courier.CourierChecker;
import ya.praktikum.courier.CourierGenerator;
import ya.praktikum.courier.CourierHelper;
import ya.praktikum.model.Courier;
import ya.praktikum.model.User;

public class CourierCreateTest {

    private final CourierHelper courierHelper = new CourierHelper();
    private final CourierGenerator courierGenerator = new CourierGenerator();
    private final CourierChecker courierChecker = new CourierChecker();


    private void cleanTestData(Courier courierData){
        User userData = User.from(courierData);
        ValidatableResponse loginResponse = courierHelper.login(userData);
        int courierId = courierChecker.extractCourierId(loginResponse);
        courierHelper.delete(courierId);
    }


    @Test
    public void createCourierSuccess() {
        Courier courierData = courierGenerator.getRandom();
        ValidatableResponse createResponse = courierHelper.create(courierData);
        courierChecker.creationSuccess(createResponse);
        cleanTestData(courierData);
    }


    @Test
    public void createIdenticalCourierFail() {
        Courier courierData = courierGenerator.getRandom();
        courierHelper.create(courierData);
        ValidatableResponse createResponse = courierHelper.create(courierData);
        courierChecker.creationFailedConflict(createResponse);
        cleanTestData(courierData);
    }


    @Test
    public void createCourierIdenticalLoginFail() {
        Courier courierData = courierGenerator.getRandom();
        courierHelper.create(courierData);
        courierData.setPassword(courierGenerator.getRandomPassword());
        courierData.setFirstName(courierGenerator.getRandomFirstName());
        ValidatableResponse createResponse = courierHelper.create(courierData);
        // Его Величество Бага
        courierChecker.creationFailedConflict(createResponse);
    }


    @Test
    public void createCourierWithoutLoginFail() {
        Courier courierData = courierGenerator.getRandom();
        courierData.setLogin(null);
        ValidatableResponse createResponse = courierHelper.create(courierData);
        courierChecker.creationFailedBadRequest(createResponse);
    }


    @Test
    public void createCourierWithoutPasswordFail() {
        Courier courierData = courierGenerator.getRandom();
        courierData.setPassword(null);
        ValidatableResponse createResponse = courierHelper.create(courierData);
        courierChecker.creationFailedBadRequest(createResponse);
    }


    @Test
    public void createCourierWithoutFirstNameSuccess() {
        Courier courierData = courierGenerator.getRandom();
        courierData.setFirstName(null);
        ValidatableResponse createResponse =courierHelper.create(courierData);
        courierChecker.creationSuccess(createResponse);
        cleanTestData(courierData);
    }

}


