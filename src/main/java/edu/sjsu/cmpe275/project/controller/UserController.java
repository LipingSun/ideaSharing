package edu.sjsu.cmpe275.project.controller;

import edu.sjsu.cmpe275.project.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser() {
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
        return null;
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