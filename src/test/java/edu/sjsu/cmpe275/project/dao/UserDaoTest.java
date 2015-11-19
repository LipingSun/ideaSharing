package edu.sjsu.cmpe275.project.dao;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jianxin on 11/18/15.
 */
public class UserDaoTest {

    UserDao userDao;

    @Test
    public void testFindById() throws Exception {
        assertEquals(userDao.findById(1), 1);
    }
}