package edu.sjsu.cmpe275.project.rest;

import edu.sjsu.cmpe275.project.domain.UserLikedIdea;
import edu.sjsu.cmpe275.project.repository.UserLikedIdeaRepository;
import edu.sjsu.cmpe275.project.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserLikedIdea.
 */
@RestController
@RequestMapping("/api")
public class UserLikedIdeaResource {

    private final Logger log = LoggerFactory.getLogger(UserLikedIdeaResource.class);

    @Inject
    private UserLikedIdeaRepository userLikedIdeaRepository;

    /**
     * POST  /userLikedIdeas -> Create a new userLikedIdea.
     */
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
     * GET  /userLikedIdeas/:id -> get the "id" userLikedIdea.
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
     * DELETE  /userLikedIdeas/:id -> delete the "id" userLikedIdea.
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
