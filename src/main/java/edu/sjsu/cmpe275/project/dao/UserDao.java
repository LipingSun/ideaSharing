package edu.sjsu.cmpe275.project.dao;

import edu.sjsu.cmpe275.project.domain.User;

import java.util.List;

/**
 * Created by jianxin on 11/18/15.
 */
public interface UserDao {
    User findById(int id);
    void store(User user);
    User update(User user);
    List<User> findAll();
}
