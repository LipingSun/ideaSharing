package edu.sjsu.cmpe275.project.dao;

import edu.sjsu.cmpe275.project.domain.Idea;

import java.util.List;

/**
 * Created by jianxin on 11/19/15.
 */
public interface IdeaDao {
    /**
     * Method to insert a new idea or update an existing one.
     * @param idea Idea object to be inserted or updated.
     */
    void store(Idea idea);

    /**
     * Method to delete an idea designated by id.
     * @param id The id of the Idea object to be deleted.
     */
    void delete(long id);

    /**
     * Method to retrieve an idea by its id;
     * @param id Id of the Idea object of interest.
     * @return Returning the desired idea object.
     */
    Idea findById(long id);

    /**
     * Method to retrieve all the stored Idea objects in the database.
     * @return A list of all the Idea records.
     */
    List<Idea> findAll();

}
