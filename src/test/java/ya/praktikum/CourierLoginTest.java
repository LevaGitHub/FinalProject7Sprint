package ya.praktikum;

import io.qameta.allure.Description;
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


public class CourierLoginTest {
    private final CourierHelper courierHelper = new CourierHelper();
    private final CourierGenerator courierGenerator = new CourierGenerator();
    private final CourierChecker courierChecker = new CourierChecker();
    private int courierId;


    @After
    public void cleanTestData(){
        if (courierId > 0) {
            courierHelper.delete(courierId);
        }
    }


    @Test
    @DisplayName("Login exists courier")
    @Description("Проверка возможности авторизации ранее созданного курьера")
    public void loginExistsCourierSuccess() {
        Courier courierData = courierGenerator.getExistsCourier();
        User userData = User.from(courierData);
        ValidatableResponse loginResponse = courierHelper.login(userData);
        courierChecker.loginSuccess(loginResponse);
    }

    @Test
    @DisplayName("Login new courier")
    @Description("Проверка возможности авторизации вновь созданного курьера")
    public void loginNewCourierSuccess() {
        Courier courierData = courierGenerator.getRandom();
        courierHelper.create(courierData);
        User userData = User.from(courierData);
        ValidatableResponse loginResponse = courierHelper.login(userData);
        courierChecker.loginSuccess(loginResponse);
        courierId = courierHelper.extractCourierId(loginResponse);
    }

    @Test
    @DisplayName("Login courier without login")
    @Description("Проверка невозможности авторизации курьера без обязательного поля Логин")
    public void loginCourierWitoutLoginFail() {
        User userData = new User(null, courierGenerator.getRandomPassword());
        ValidatableResponse loginResponse = courierHelper.login(userData);
        courierChecker.loginFailBadRequest(loginResponse);
    }

    @Test
    @DisplayName("Login courier without login")
    @Description("Проверка невозможности авторизации курьера без обязательного поля Пароль")
    public void loginCourierWitoutPasswordFail() {
        User userData = new User(courierGenerator.getRandomLogin(), null);
        ValidatableResponse loginResponse = courierHelper.login(userData);
        courierChecker.loginFailBadRequest(loginResponse); // Отваливается по таймауту, 504 ;
    }


    @Test
    @DisplayName("Login courier with incorrect login")
    @Description("Проверка невозможности авторизации курьера с несуществующим логином")
    public void loginCourierIncorrectLoginFail() {
        Courier courierData = courierGenerator.getRandom();
        courierHelper.create(courierData);
        User userData = new User(courierGenerator.getRandomLogin(), courierData.getPassword());
        ValidatableResponse loginResponse = courierHelper.login(userData);
        courierChecker.loginFailDataNotFound(loginResponse);
        courierId = courierHelper.getCourierId(courierData);
    }


    @Test
    @DisplayName("Login courier with incorrect login")
    @Description("Проверка невозможности авторизации курьера с несуществующим логином")
    public void loginCourierIncorrectPasswordFail() {
        Courier courierData = courierGenerator.getRandom();
        courierHelper.create(courierData);
        User userData = new User(courierData.getLogin(), courierGenerator.getRandomPassword());
        ValidatableResponse loginResponse = courierHelper.login(userData);
        courierChecker.loginFailDataNotFound(loginResponse);
        courierId = courierHelper.getCourierId(courierData);
    }


    @Test
    public void loginCourierIncorrectLoginAndPasswordFail() {
        User userData = new User(courierGenerator.getRandomLogin(), courierGenerator.getRandomPassword());
        ValidatableResponse loginResponse = courierHelper.login(userData);
        courierChecker.loginFailDataNotFound(loginResponse);
    }
}


