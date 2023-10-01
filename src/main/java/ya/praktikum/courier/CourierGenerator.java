package ya.praktikum.courier;

import io.qameta.allure.Step;
import ya.praktikum.model.Courier;

import java.util.UUID;

public class CourierGenerator {

    @Step("Получение данных уже созданного курьера")
    public Courier getExistsCourier() {
        return new Courier(
                "java_autotest_login_exists",
                "java_autotest_password_exists",
                "java_autotest_first_name_exists");
    }

    @Step("Генерация уникальных данных курьера")
    public Courier getRandom() {
        return new Courier(
                getRandomLogin(),
                getRandomPassword(),
                getRandomFirstName());
    }

    public String getRandomLogin(){
        return "java_autotest_login_" + UUID.randomUUID();
    }

    public String getRandomPassword(){
        return "java_autotest_password_" + UUID.randomUUID();
    }

    public String getRandomFirstName(){
        return "java_autotest_first_name_" + UUID.randomUUID();
    }

}
