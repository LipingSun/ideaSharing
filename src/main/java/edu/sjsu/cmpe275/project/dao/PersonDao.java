package edu.sjsu.cmpe275.project.dao;

import edu.sjsu.cmpe275.project.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao{

    private SessionFactory sessionFactory;

    public PersonDao() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public Person findById(long personId) {
        Session session = sessionFactory.openSession();
        try {
            Person person = session.get(Person.class, personId);
            if (person == null) {
                throw new RuntimeException("ID_NOT_EXIST");
            }
            return person;
        } finally {
            session.close();
        }
    }

}
