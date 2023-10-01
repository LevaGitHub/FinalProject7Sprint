package ya.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Ignore;
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
    private int courierId;

    @After
    @Step("Очистка тестовых данных")
    public void cleanTestData(){
        if (courierId > 0) {
            courierHelper.delete(courierId);
        }
    }


    @Test
    @DisplayName("Create courier")
    @Description("Проверка создания курьера с корректными данными")
    public void createCourierSuccess() {
        Courier courierData = courierGenerator.getRandom();
        ValidatableResponse createResponse = courierHelper.create(courierData);
        courierChecker.creationSuccess(createResponse);
        courierId = courierHelper.getCourierId(courierData);
    }


    @Test
    @DisplayName("Create identical courier")
    @Description("Проверка невозможности создания двух одинаковых курьеров")
    public void createIdenticalCourierFail() {
        Courier courierData = courierGenerator.getRandom();
        courierHelper.create(courierData);
        ValidatableResponse createResponse = courierHelper.create(courierData);
        courierChecker.creationFailedConflict(createResponse);
        courierId = courierHelper.getCourierId(courierData);
    }


    @Test
    @DisplayName("Create identical login courier")
    @Description("Проверка невозможности создания двух курьеров с одинаковым логином")
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
    @DisplayName("Create courier without login")
    @Description("Проверка создания курьера с пустым полем Логин")
    public void createCourierWithoutLoginFail() {
        Courier courierData = courierGenerator.getRandom();
        courierData.setLogin(null);
        ValidatableResponse createResponse = courierHelper.create(courierData);
        courierChecker.creationFailedBadRequest(createResponse);
    }


    @Test
    @DisplayName("Create courier without password")
    @Description("Проверка создания курьера с пустым полем Пароль")
    public void createCourierWithoutPasswordFail() {
        Courier courierData = courierGenerator.getRandom();
        courierData.setPassword(null);
        ValidatableResponse createResponse = courierHelper.create(courierData);
        courierChecker.creationFailedBadRequest(createResponse);
    }


    @Test
    @DisplayName("Create courier without firstname")
    @Description("Проверка создания курьера с пустым полем Фамилия")
    public void createCourierWithoutFirstNameSuccess() {
        Courier courierData = courierGenerator.getRandom();
        courierData.setFirstName(null);
        ValidatableResponse createResponse =courierHelper.create(courierData);
        courierChecker.creationSuccess(createResponse);
        courierId = courierHelper.getCourierId(courierData);
    }

}


