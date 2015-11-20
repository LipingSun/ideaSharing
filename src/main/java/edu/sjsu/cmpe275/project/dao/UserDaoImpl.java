package edu.sjsu.cmpe275.project.dao;


import edu.sjsu.cmpe275.project.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by jianxin on 11/19/15.
 */
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    /**
     * Constructor.
     */
    public UserDaoImpl() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    /**
     * Method to store(insert or update) an User object in the database.
     *
     * @param user The User object to be stored.
     */
    @Override
    public void store(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try{
            tx.begin();
            session.saveOrUpdate(user);
            tx.commit();
        }catch(RuntimeException e){
            tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    /**
     * Method to delete an user object from the database.
     *
     * @param id The id of the User object to be deleted.
     */
    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try{
            tx.begin();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            tx.commit();
        }catch(RuntimeException e){
            tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    /**
     * Method to retrieve an User object from the database designated by the id.
     *
     * @param id Id of the User object of interest.
     * @return The retrieved User object.
     */
    @Override
    public User findById(long id) {
        Session session = sessionFactory.openSession();
        try{
            return session.get(User.class, id);
        }finally {
            session.close();
        }
    }

    /**
     * Method to retrieve all the user objects stored in the database.
     *
     * @return A list of all the User records.
     */
    @Override
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery("from User");
            return query.list();
        }finally {
            session.close();
        }
    }
}
