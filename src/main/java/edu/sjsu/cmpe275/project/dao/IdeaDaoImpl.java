package edu.sjsu.cmpe275.project.dao;

import edu.sjsu.cmpe275.project.domain.Idea;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by jianxin on 11/19/15.
 */
public class IdeaDaoImpl implements IdeaDao {

    private SessionFactory sessionFactory;

    /**
     * Constructor.
     */
    public IdeaDaoImpl() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    /**
     * Method to insert a new idea or update an existing one.
     *
     * @param idea Idea object to be inserted or updated.
     */
    @Override
    public void store(Idea idea) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try{
            tx.begin();
            session.saveOrUpdate(idea);
            tx.commit();
        }catch(RuntimeException e){
            tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    /**
     * Method to delete an idea designated by id.
     *
     * @param id The id of the Idea object to be deleted.
     */
    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try{
            tx.begin();
            Idea idea = (Idea) session.get(Idea.class, id);
            session.delete(idea);
            tx.commit();
        }catch(RuntimeException e){
            tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    /**
     * Method to retrieve an idea by its id;
     *
     * @param id Id of the Idea object of interest.
     * @return Returning the desired idea object.
     */
    @Override
    public Idea findById(long id) {
        Session session = sessionFactory.openSession();
        try{
            return session.get(Idea.class, id);
        }finally {
            session.close();
        }
    }

    /**
     * Method to retrieve all the stored Idea objects in the database.
     *
     * @return A list of all the Idea records.
     */
    @Override
    public List<Idea> findAll() {
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery("from Idea");
            return query.list();
        }finally {
            session.close();
        }
    }
}
