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
        int i = userService.listUsers().size();
        for (int a = 1; a < 11; a++) {
            User user = new User("User" + (a + i), "Lastname" + (a + i), "user" + (a + i) + "@mail.ru");
            Car car = new Car("model" + (a + i), a + i);
            car.setUser(user);
            userService.add(user);
            userService.add(car);
        }
//        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        User userByModel = userService.getUserByCarModelAndSeries("model10", 10);
        System.out.println("Id = " + userByModel.getId());
        System.out.println("First Name = " + userByModel.getFirstName());
        System.out.println("Last Name = " + userByModel.getLastName());
        System.out.println("Email = " + userByModel.getEmail());
        System.out.println();

        context.close();
    }
}
