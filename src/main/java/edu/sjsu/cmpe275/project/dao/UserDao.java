package edu.sjsu.cmpe275.project.dao;

import edu.sjsu.cmpe275.project.domain.User;

import java.util.List;

/**
 * Created by jianxin on 11/18/15.
 */

public interface UserDao {
    void store(User user);
    void delete(long id);
    User findById(long id);
    List<User> findAll();
}
