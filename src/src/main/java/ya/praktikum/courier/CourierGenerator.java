package ya.praktikum.courier;

import ya.praktikum.model.Courier;

import java.util.UUID;

public class CourierGenerator {
    public Courier getExistsCourier() {
        return new Courier(
                "java_autotest_login_exists",
                "java_autotest_password_exists",
                "java_autotest_first_name_exists");
    }

    public Courier getRandom() {
        UUID uuid = UUID.randomUUID();
        return new Courier(
                "java_autotest_login_" + uuid,
                "java_autotest_password_" + uuid,
                "java_autotest_first_name_" + uuid);
    }
}
