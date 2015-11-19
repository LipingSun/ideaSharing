package edu.sjsu.cmpe275.project.controller;

import edu.sjsu.cmpe275.project.domain.Idea;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiaotong on 11/19/15.
 */
@RequestMapping("/api/v1/users/{user_id}/ideas*")
@RestController
public class IdeaController {


    /**
     * Get a users' ideas
     * @param userId
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getIdeas(@PathVariable("user_id") long userId){
        if(userId == 1){
            return new ResponseEntity<>(new Idea("Good Idea","Good ideas of this user!"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Create a new idea
     * @param userId
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createIdea(@PathVariable("user_id") long userId){
        if(userId == 1){
            return new ResponseEntity<>(new Idea("Good Idea","This good idea is created!"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Get a user's idea by ides's id
     * @param userId
     * @param id (idea's id)
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getIdeaById(@PathVariable("user_id") long userId , @PathVariable("id") long id){
        if(userId == 1){
            return new ResponseEntity<>(new Idea("Good Idea","This is this user's one good idea!"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update a user's idea by idea's id
     * @param userId
     * @param id (idea's id)
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateIdeaById(@PathVariable("user_id") long userId , @PathVariable("id") long id){
        if(userId == 1){
            return new ResponseEntity<>(new Idea("Good Idea","This good idea is updated!"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
