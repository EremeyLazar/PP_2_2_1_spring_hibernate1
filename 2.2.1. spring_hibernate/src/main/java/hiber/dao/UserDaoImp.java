package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCarModelAndSerie(String model, int series) {
      Query nativeQuery
              = sessionFactory.getCurrentSession().createNativeQuery("SELECT * FROM `car` WHERE `MODEL` = :paramModel AND series = :paramSeries", Car.class);
      nativeQuery.setParameter("paramModel", model);
      nativeQuery.setParameter("paramSeries", series);
      Car car =  (Car) nativeQuery.getSingleResult();
      User user = car.getUser();
      return new user ;
   }
}

