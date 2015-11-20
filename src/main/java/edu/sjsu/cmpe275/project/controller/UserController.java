package edu.sjsu.cmpe275.project.controller;

import edu.sjsu.cmpe275.project.dao.UserDao;
import edu.sjsu.cmpe275.project.dao.UserDaoImpl;
import edu.sjsu.cmpe275.project.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Liping on 11/19/15.
 */
@RequestMapping("/api/v1/users*")
@RestController
public class UserController {

    private UserDao userDao;
    public UserController(){
        userDao = new UserDaoImpl();
    }
    /**
     * POST /api/v1/users
     * Create a user
     * @return Created user
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestParam("username") String userName,
                                        @RequestParam("password") String pwd,
                                        @RequestParam("email") String email,
                                        @RequestParam("description") String dsp)
    {
        User user = new User(userName, email,/* pwd,*/ dsp);
        try {
            userDao.store(user);
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * GET /api/v1/users/{id}
     * Get a user by user id
     * @param userId user id
     * @return Found user
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") int userId) {
        try {
            User user = userDao.findById(userId);
            return new ResponseEntity<Object>(user, user==null?HttpStatus.NOT_FOUND:HttpStatus.OK);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * POST /api/v1/users/{id}
     * Update a user by user id
     * @param userId user id
     * @return Updated user
     */
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@PathVariable("id") int userId,
                                        @RequestParam("username") String userName,
                                        @RequestParam("email") String email,
                                        @RequestParam("description") String dsp) {
        try {
            User user = userDao.findById(userId);
            if(user == null)
                return new ResponseEntity<Object>(user, HttpStatus.NOT_FOUND);
            user.setUsername(userName);
            user.setEmail(email);
            user.setDescription(dsp);
            User newUser = userDao.update(user);
            return new ResponseEntity<Object>(newUser, HttpStatus.OK);
        }catch (Exception e){
            throw e;
        }
    }

}