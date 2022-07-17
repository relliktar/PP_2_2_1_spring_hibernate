package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        userService.clearTable();

        for (int a = 1; a < 11; a++) {
            User user = new User(
                    "User" + a,
                    "Lastname" + a,
                    "user" + a + "@mail.ru");
            userService.add(user);
            if (a % 2 == 0) {
                Car car = new Car("model" + a, a);
                car.setUser(user);
                userService.add(car);
            }
        }

        System.out.println();

        List<User> users = userService.listUsers();
        System.out.println();

        for (User user : users) {
            System.out.println("    " + user);
        }
        System.out.println();
        User userByModel = userService.getUserByCarModelAndSeries("model10", 10);

        System.out.println("\n      " + userByModel);

        context.close();
    }
}
