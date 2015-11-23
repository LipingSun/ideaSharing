package edu.sjsu.cmpe275.project.rest;

import edu.sjsu.cmpe275.project.domain.UserReadIdea;
import edu.sjsu.cmpe275.project.repository.UserReadIdeaRepository;
import edu.sjsu.cmpe275.project.rest.util.HeaderUtil;
import edu.sjsu.cmpe275.project.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
 * REST controller for managing UserReadIdea.
 */
@RestController
@RequestMapping("/api")
public class UserReadIdeaResource {

    private final Logger log = LoggerFactory.getLogger(UserReadIdeaResource.class);

    @Inject
    private UserReadIdeaRepository userReadIdeaRepository;

    /**
     * POST  /userReadIdeas -> Create a new userReadIdea.
     */
    @RequestMapping(value = "/userReadIdeas",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<UserReadIdea> createUserReadIdea(@RequestBody UserReadIdea userReadIdea) throws URISyntaxException {
        log.debug("REST request to save UserReadIdea : {}", userReadIdea);
        if (userReadIdea.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new userReadIdea cannot already have an ID").body(null);
        }
        UserReadIdea result = userReadIdeaRepository.save(userReadIdea);
        return ResponseEntity.created(new URI("/api/userReadIdeas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("userReadIdea", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /userReadIdeas -> Updates an existing userReadIdea.
     */
    @RequestMapping(value = "/userReadIdeas",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<UserReadIdea> updateUserReadIdea(@RequestBody UserReadIdea userReadIdea) throws URISyntaxException {
        log.debug("REST request to update UserReadIdea : {}", userReadIdea);
        if (userReadIdea.getId() == null) {
            return createUserReadIdea(userReadIdea);
        }
        UserReadIdea result = userReadIdeaRepository.save(userReadIdea);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("userReadIdea", userReadIdea.getId().toString()))
            .body(result);
    }

    /**
     * GET  /userReadIdeas -> get all the userReadIdeas.
     */
    @RequestMapping(value = "/userReadIdeas",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<UserReadIdea>> getAllUserReadIdeas(Pageable pageable)
        throws URISyntaxException {
        Page<UserReadIdea> page = userReadIdeaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/userReadIdeas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /userReadIdeas/:id -> get the "id" userReadIdea.
     */
    @RequestMapping(value = "/userReadIdeas/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<UserReadIdea> getUserReadIdea(@PathVariable Long id) {
        log.debug("REST request to get UserReadIdea : {}", id);
        return Optional.ofNullable(userReadIdeaRepository.findOne(id))
            .map(userReadIdea -> new ResponseEntity<>(
                userReadIdea,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /userReadIdeas/:id -> delete the "id" userReadIdea.
     */
    @RequestMapping(value = "/userReadIdeas/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> deleteUserReadIdea(@PathVariable Long id) {
        log.debug("REST request to delete UserReadIdea : {}", id);
        userReadIdeaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("userReadIdea", id.toString())).build();
    }
}
