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


//      userService.add(new User("User6", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User7", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User8", "Lastname4", "user4@mail.ru"));

//      User user1 = new User("Shurik", "Tamilov", "shurik@mail.su");
//      Car car = new Car("Zhigul", 12002316);
//      user1.setCar(car);
//      userService.add(user1);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("User's car is:" + user.getCar());
         System.out.println();
      }

      User user = userService.getUserByCarModelAndSerie("Moskvich", 666);
      System.out.println(user);



      context.close();
   }
}
