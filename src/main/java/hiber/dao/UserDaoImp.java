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
//      String a = "FROM User u JOIN FETCH u.car AS car WHERE car.series = :series AND car.model = :model";
        String queryText = "SELECT car.user FROM Car car WHERE car.series = :series AND car.model = :model";
        Query<User> query = sessionFactory.getCurrentSession().createQuery(queryText, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getSingleResult();
    }

    public void clearTable() {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("FROM User", User.class)
                .getResultList()
                .forEach(session::delete);
        session.flush();
    }
}
