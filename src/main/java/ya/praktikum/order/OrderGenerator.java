package ya.praktikum.order;

import org.apache.commons.lang3.RandomStringUtils;
import ya.praktikum.model.Order;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class OrderGenerator {

    public Order getRandom() {
        UUID uuid = UUID.randomUUID();

        LocalDate currentDate = LocalDate.now();
        return new Order(
                String.format("Имя заказчика %s", uuid),
                String.format("Фамилия заказчика %s", uuid),
                String.format("Адрес заказчика%s", uuid),
                50,
                String.format("Телефон заказчика%s", uuid),
                9,
                currentDate.toString(),
                String.format("Комментарий от заказчика%s", uuid),
                List.of("")
        );

    }
}
