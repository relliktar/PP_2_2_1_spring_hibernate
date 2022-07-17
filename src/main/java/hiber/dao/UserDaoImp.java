package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCarModelAndSeries(String model, int series) {
        Query<Car> query = sessionFactory.getCurrentSession()
                .createQuery("FROM Car car WHERE car.series = :series AND car.model = :model", Car.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getSingleResult().getUser();
    }

    public void clearTable() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("FROM User", User.class).getResultList();
        for (User user : users) {
            session.delete(user);
        }
        session.flush();
    }
}
