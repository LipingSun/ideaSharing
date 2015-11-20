package edu.sjsu.cmpe275.project.dao;

import edu.sjsu.cmpe275.project.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;


/**
 * Created by jianxin on 11/18/15.
 */
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;
    public UserDaoImpl() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public User findById(int id) {
        Session session = sessionFactory.openSession();
        try {
            User user = (User) session.get(User.class,id);
            if (user == null){
                throw new RuntimeException("ID_NOT_EXIST");
            }
            return user;
        } finally {
            session.close();
        }
    }
/*
--    @Override
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
*/

    /*
    @Override
    public void store(Person person) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            if (person.getOrganization() != null) {
                Organization org = session.get(Organization.class, person.getOrganization().getId());
                if (org != null) {
                    person.setOrganization(org);
                } else {
                    throw new RuntimeException("ORG_NOT_EXIST");
                }
            }
            session.save(person);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    */
    @Override
    public void store(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try{
            tx.begin();

        }catch (RuntimeException e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}

/*
public class HibernatePersonDao implements PersonDao {

--    /*
    private SessionFactory sessionFactory; //Does it need to add anything to enable the sessionFactory besides import...?

    public HibernatePersonDao() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    */

    /*
    @Override
    public void store(Person person) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            if (person.getOrganization() != null) {
                Organization org = session.get(Organization.class, person.getOrganization().getId());
                if (org != null) {
                    person.setOrganization(org);
                } else {
                    throw new RuntimeException("ORG_NOT_EXIST");
                }
            }
            session.save(person);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    */

    /*
    public Person update(Person personChanges) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Person person = session.get(Person.class, personChanges.getId());
            if (person == null) {
                throw new RuntimeException("ID_NOT_EXIST");
            }
            person.setEmail(personChanges.getEmail());
            if (personChanges.getFirstname() != null) {
                person.setFirstname(personChanges.getFirstname());
            }
            if (personChanges.getLastname() != null) {
                person.setLastname(personChanges.getLastname());
            }
            if (personChanges.getDescription() != null) {
                person.setDescription(personChanges.getDescription());
            }
            if (personChanges.getAddress() != null) {
                person.setAddress(personChanges.getAddress());
            }
            if (personChanges.getOrganization() != null) {
                Organization org = session.get(Organization.class, personChanges.getOrganization().getId());
                if (org != null) {
                    person.setOrganization(org);
                } else {
                    throw new RuntimeException("ORG_NOT_EXIST");
                }
            }
            session.update(person);
            tx.commit();
            return person;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    */
    /*
    @Override
    public Person delete(long personId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Person person = session.get(Person.class, personId);
            if (person == null) {
                throw new RuntimeException("ID_NOT_EXIST");
            }
            Person personClone = new Person(person);
            List<Person> friends = person.getFriends();
            for (Person friend : friends) {
                friend.getFriends().remove(person);
                session.update(friend);
            }
            friends.clear();
            session.update(person);
            session.delete(person);
            tx.commit();
            return personClone;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    */
    /*
--    @Override
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
    */
    /*
    @Override
    public List<Person> findAll() {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from Person");
            return query.list();
        } finally {
            session.close();
        }
    }
}
*/
