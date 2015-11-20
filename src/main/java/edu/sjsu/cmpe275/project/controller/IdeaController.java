package edu.sjsu.cmpe275.project.controller;

import edu.sjsu.cmpe275.project.dao.IdeaDao;
import edu.sjsu.cmpe275.project.dao.IdeaDaoImpl;
import edu.sjsu.cmpe275.project.domain.Idea;
import edu.sjsu.cmpe275.project.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiaotong on 11/19/15.
 */
@RequestMapping("/api/v1/users/{user_id}/ideas*")
@RestController
public class IdeaController {


    private IdeaDao ideaDao;
    public IdeaController(){
        ideaDao = new IdeaDaoImpl();
    }
//
//    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> createUser(@RequestParam("username") String userName,
//                                        @RequestParam("password") String pwd,
//                                        @RequestParam("email") String email,
//                                        @RequestParam("description") String dsp)
//    {
//        User user = new User(userName, email,/* pwd,*/ dsp);
//        try {
//            userDao.store(user);
//            return new ResponseEntity<Object>(user, HttpStatus.OK);
//        }catch (Exception e){
//            throw e;
//        }
//    }

    /**
     * Get a users' ideas
     * @param userId : user's id
     * @return : all ideas created by this user
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getIdeas(@PathVariable("user_id") long userId){
        if(userId == 1){
            return new ResponseEntity<>(new Idea("Good Idea","Good ideas of this user!"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
//        Idea idea = ideaDao.
    }


    /**
     * Create a new idea
     * @param userId : user's id
     * @return : create a new idea
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
     * @param id : idea's id
     * @return :
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
     * @param userId : user's id
     * @param id : idea's id
     * @return : updated idea
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
