package edu.sjsu.cmpe275.project.dao;

/**
 * Created by jianxin on 11/18/15.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public int findById(int id) {
        if (id == 1) {
            return 1;
        }
        return 0;
    }
}
