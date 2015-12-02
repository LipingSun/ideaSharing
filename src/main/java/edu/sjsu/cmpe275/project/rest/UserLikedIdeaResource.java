package edu.sjsu.cmpe275.project.rest;

import edu.sjsu.cmpe275.project.domain.Idea;
import edu.sjsu.cmpe275.project.domain.User;
import edu.sjsu.cmpe275.project.domain.UserLikedIdea;
import edu.sjsu.cmpe275.project.repository.IdeaRepository;
import edu.sjsu.cmpe275.project.repository.UserLikedIdeaRepository;
import edu.sjsu.cmpe275.project.repository.UserRepository;
import edu.sjsu.cmpe275.project.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserLikedIdea. Chang's part
 */
@RestController
@RequestMapping("/api")
public class UserLikedIdeaResource {

    private final Logger log = LoggerFactory.getLogger(UserLikedIdeaResource.class);

    @Inject
    private UserLikedIdeaRepository userLikedIdeaRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private IdeaRepository ideaRepository;


    @RequestMapping(value = "/userLikedIdeas",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<UserLikedIdea> createUserLikedIdea(@RequestBody UserLikedIdea userLikedIdea) throws URISyntaxException {
        log.debug("REST request to save UserLikedIdea : {}", userLikedIdea);
        if (userLikedIdea.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new userLikedIdea cannot already have an ID").body(null);
        }
        UserLikedIdea result = userLikedIdeaRepository.save(userLikedIdea);
        return ResponseEntity.created(new URI("/api/userLikedIdeas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("userLikedIdea", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /userLikedIdeas -> Updates an existing userLikedIdea.
     */
    @RequestMapping(value = "/userLikedIdeas",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<UserLikedIdea> updateUserLikedIdea(@RequestBody UserLikedIdea userLikedIdea) throws URISyntaxException {
        log.debug("REST request to update UserLikedIdea : {}", userLikedIdea);
        if (userLikedIdea.getId() == null) {
            return createUserLikedIdea(userLikedIdea);
        }
        UserLikedIdea result = userLikedIdeaRepository.save(userLikedIdea);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("userLikedIdea", userLikedIdea.getId().toString()))
            .body(result);
    }

    /**
     * GET  /userLikedIdeas -> get all the userLikedIdeas.
     */
    @RequestMapping(value = "/userLikedIdeas",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public List<UserLikedIdea> getAllUserLikedIdeas() {
        log.debug("REST request to get all UserLikedIdeas");
        return userLikedIdeaRepository.findAll();
    }

    /**
     * GET  /userLikedIdeas/:idea_id -> get the "idea_id" userLikedIdea.
     */
    @RequestMapping(value = "/userLikedIdeas/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<UserLikedIdea> getUserLikedIdea(@PathVariable Long id) {
        log.debug("REST request to get UserLikedIdea : {}", id);
        return Optional.ofNullable(userLikedIdeaRepository.findOne(id))
            .map(userLikedIdea -> new ResponseEntity<>(
                userLikedIdea,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET  /users/{user_id}/like -> get all ideas from a specific user through user_id.
     */
    @RequestMapping(value = "users/{user_id}/like",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public List<UserLikedIdea> getLikedIdeas(@PathVariable Long user_id) {
        log.debug("REST request to get user_id based UserLikedIdea : {}", user_id);
        User user = userRepository.findOne(user_id);
        return userLikedIdeaRepository.findByUser(user);
    }


    /**
     * GET  /ideas/{idea_id}/like -> get all users those like a specific idea through idea_id.
     */
    @RequestMapping(value = "ideas/{idea_id}/like",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)

    public List<UserLikedIdea> getUsers(@PathVariable Long idea_id) {
        log.debug("REST request to get idea_id based UserLikedIdea :{}", idea_id);
        Idea idea = ideaRepository.findOne(idea_id);
        return userLikedIdeaRepository.findByIdea(idea);
    }

//start from here to continue, hasn't been tested with postman yet
    /**
     * PUT  /like/{idea_id}/{user_id} -> create an entry with a specific pair of idea_id and user_id.
     */
    @RequestMapping(value = "like/{idea_id}/{user_id}",
                    method = RequestMethod.PUT,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLikedIdea> createUserLikedIdea(@PathVariable Long idea_id, @PathVariable Long user_id) throws URISyntaxException{
        log.debug("REST request to create UserLikedIdea : {} : {}", idea_id, user_id);
        UserLikedIdea userLikedIdea = new UserLikedIdea();
        User user = userRepository.findOne(user_id);
        Idea idea = ideaRepository.findOne(idea_id);
        userLikedIdea.setUser(user);
        userLikedIdea.setIdea(idea);
        return ResponseEntity.ok().
                headers(HeaderUtil.createEntityUpdateAlert("userLikedIdea", userLikedIdea.getId().toString()))
                .body(userLikedIdea);
    }
//    /**
//     * PUT  /users -> Updates an existing user.
//     */
//    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) throws URISyntaxException {
//        log.debug("REST request to update User : {}", user);
//        if (user.getId() == null) {
//            return createUser(user);
//        }
//        User result = userRepository.save(user);
//        return ResponseEntity.ok()
//                .headers(HeaderUtil.createEntityUpdateAlert("user", user.getId().toString()))
//                .body(result);
//    }

    /**
     * DELETE  /like/{idea_id}/{user_id} -> Delete an entry with a specific pair of idea_id and user_id.
     */
    @RequestMapping(value = "like/{idea_id}/{user_id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> deleteUserLikedIdea(@PathVariable Long idea_id, @PathVariable Long user_id) {
        log.debug("REST request to delete the pair UserLikedIdea : {} : {}", idea_id, user_id);
        Idea idea = ideaRepository.findOne(idea_id);
        List<UserLikedIdea> userLikedIdeaList = userLikedIdeaRepository.findByIdea(idea);
        UserLikedIdea target = new UserLikedIdea();
        for (UserLikedIdea item : userLikedIdeaList) {
            if(item.getUser() == userRepository.findOne(user_id)) {
                target = item;
                break;
            }
        }

        Long id = target.getId();
        userLikedIdeaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("userLikedIdea", id.toString())).build();
    }


    /**
     * DELETE  /userLikedIdeas/:idea_id -> delete the "idea_id" userLikedIdea.
     */
    @RequestMapping(value = "/userLikedIdeas/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> deleteUserLikedIdea(@PathVariable Long id) {
        log.debug("REST request to delete UserLikedIdea : {}", id);
        userLikedIdeaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("userLikedIdea", id.toString())).build();
    }
}
