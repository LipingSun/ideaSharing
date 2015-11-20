package edu.sjsu.cmpe275.project.dao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jianxin on 11/18/15.
 */
public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void testFindById() throws Exception {
        assertEquals(userDao.findById(1), 1);
    }

    public void testStore() throws Exception {

    }

    public void testDelete() throws Exception {

    }

    public void testUpdate() throws Exception {

    }

    public void testFindAll() throws Exception {

    }
}