package edu.sjsu.cmpe275.project.controller;

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
//        User user = new User(userName)
        return null;
    }

    /**
     * GET /api/v1/users/{id}
     * Get a user by user id
     * @param userId user id
     * @return Found user
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long userId) {
        if (userId == 1) {
            return new ResponseEntity<>(new User(1, "user1", "user1@gmail.com", "This is user1"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * POST /api/v1/users/{id}
     * Update a user by user id
     * @param userId user id
     * @return Updated user
     */
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@PathVariable("id") long userId) {
        return null;
    }

}