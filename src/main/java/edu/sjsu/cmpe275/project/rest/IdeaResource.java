package edu.sjsu.cmpe275.project.rest;

import edu.sjsu.cmpe275.project.domain.Idea;
import edu.sjsu.cmpe275.project.repository.IdeaRepository;
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
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Idea.
 */
@RestController
@RequestMapping("/api")
public class IdeaResource {

    private final Logger log = LoggerFactory.getLogger(IdeaResource.class);

    @Inject
    private IdeaRepository ideaRepository;

    /**
     * POST  /ideas -> Create a new idea.
     */
    @RequestMapping(value = "/ideas",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Idea> createIdea(@Valid @RequestBody Idea idea) throws URISyntaxException {
        log.debug("REST request to save Idea : {}", idea);
        if (idea.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new idea cannot already have an ID").body(null);
        }
        Idea result = ideaRepository.save(idea);
        return ResponseEntity.created(new URI("/api/ideas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("idea", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ideas -> Updates an existing idea.
     */
    @RequestMapping(value = "/ideas",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Idea> updateIdea(@Valid @RequestBody Idea idea) throws URISyntaxException {
        log.debug("REST request to update Idea : {}", idea);
        if (idea.getId() == null) {
            return createIdea(idea);
        }
        Idea result = ideaRepository.save(idea);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("idea", idea.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ideas -> get all the ideas.
     */
    @RequestMapping(value = "/ideas",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Idea>> getAllIdeas(Pageable pageable)
        throws URISyntaxException {
        Page<Idea> page = ideaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ideas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ideas/:id -> get the "id" idea.
     */
    @RequestMapping(value = "/ideas/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Idea> getIdea(@PathVariable Long id) {
        log.debug("REST request to get Idea : {}", id);
        return Optional.ofNullable(ideaRepository.findOne(id))
            .map(idea -> new ResponseEntity<>(
                idea,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /ideas/:id -> delete the "id" idea.
     */
    @RequestMapping(value = "/ideas/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> deleteIdea(@PathVariable Long id) {
        log.debug("REST request to delete Idea : {}", id);
        ideaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("idea", id.toString())).build();
    }
}
