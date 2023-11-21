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

        User rus = new User("Владимир", "Путин", "vovan@mail.ru");
        User us = new User("Joe", "Biden", "JoeBi@aol.com");
        User fr = new User("Emmanuel", "Macron", "emmy@inbox.fr");
        User af = new User("اشرف", "غني", "rais@moic.gov.af");

        Car carRus = new Car("Moskvich", 2140);
        Car carUs = new Car("Mustang", 666);
        Car carFr = new Car("Renau", 1001011);
        Car carAf = new Car("T", 90);

        rus.setCar(carRus);
        us.setCar(carUs);
        fr.setCar(carFr);
        af.setCar(carAf);

        userService.add(rus);
        userService.add(us);
        userService.add(fr);
        userService.add(af);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("User's car is:" + user.getCar());
            System.out.println();
        }

        User user = userService.getUserByCarModelAndSerie("Mustang", 666);
        System.out.println(user);
        System.out.println();


        context.close();
    }
}
