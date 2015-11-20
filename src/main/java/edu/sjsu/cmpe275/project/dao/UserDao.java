package edu.sjsu.cmpe275.project.dao;

import edu.sjsu.cmpe275.project.domain.User;

import java.util.List;

/**
 * Created by jianxin on 11/18/15.
 */

public interface UserDao {

    /**
     * Method to store(insert or update) an User object in the database.
     *
     * @param user The User object to be stored.
     */
    void store(User user);

    /**
     * Method to delete an user object from the database.
     *
     * @param id The id of the User object to be deleted.
     */
    void delete(long id);

    /**
     * Method to retrieve an User object from the database designated by the id.
     *
     * @param id Id of the User object of interest.
     * @return The retrieved User object.
     */
    User findById(long id);

    /**
     * Method to retrieve all the user objects stored in the database.
     *
     * @return A list of all the User records.
     */
    List<User> findAll();
}
