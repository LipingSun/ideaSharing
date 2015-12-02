package edu.sjsu.cmpe275.project.rest;

import edu.sjsu.cmpe275.project.domain.Category;
import edu.sjsu.cmpe275.project.domain.Idea;
import edu.sjsu.cmpe275.project.domain.User;
import edu.sjsu.cmpe275.project.repository.CategoryRepository;
import edu.sjsu.cmpe275.project.repository.IdeaRepository;
import edu.sjsu.cmpe275.project.repository.UserRepository;
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
import java.time.ZonedDateTime;
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

    @Inject
    private UserRepository userRepository;

    @Inject
    private CategoryRepository categoryRepository;


    /**
     * POST  /ideas -> Create a new idea by a user.
     */
    @RequestMapping(value = "/ideas*",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Idea> createIdea(@RequestBody Idea idea, @RequestParam (value ="user_id") long user_id) throws URISyntaxException {
        log.debug("REST request to save Idea : {}", idea);

        if (idea.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new idea cannot already have an ID").body(null);
        }
        User user = userRepository.findOne(user_id);

        if(user == null)  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        idea.setUser(user);
        idea.setDatetime(ZonedDateTime.now());
        Idea result = ideaRepository.save(idea);
        return ResponseEntity.created(new URI("/api/ideas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("idea", result.getId().toString()))

            .body(result);
    }

    /**
     * PUT  /ideas -> Updates an existing idea.
     */
    @RequestMapping(value = "/ideas/{id}",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Idea> updateIdea(@Valid @RequestBody Idea idea, @PathVariable("id") long id ) throws URISyntaxException {
        log.debug("REST request to update Idea : {}", idea);
        if(idea.getUser() == null){
            return ResponseEntity.badRequest().header("Failure", "This idea does not have a owner").body(null);
        }
        if (idea.getId() == null || ideaRepository.findOne(idea.getId()) == null) {
            return createIdea(idea , idea.getUser().getId());
        }
        Idea result = ideaRepository.save(idea);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("idea", idea.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ideas -> get all the ideas ( of a user).
     */
    @RequestMapping(value = "/ideas",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Idea>> getAllIdeas(Pageable pageable , @RequestParam (value ="user_id",required = false) String user_id)
        throws URISyntaxException {
        if(user_id == null){
            Page<Idea> page = ideaRepository.findAll(pageable);
//            log.debug("sss"+user_id);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ideas");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }else{

            User user = userRepository.findOne(Long.parseLong(user_id));
            if(user == null)  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            List<Idea> list = ideaRepository.findAllByUser_id(user);
            return Optional.ofNullable(list)
                .map(user_ideas -> new ResponseEntity<>(
                        list,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

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
        if(ideaRepository.findOne(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ideaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("idea", id.toString())).build();
    }


    @RequestMapping(value = "/ideas", method = RequestMethod.GET, params = {"category_id","order"}, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Idea>> getIdeasByCatagory(@RequestParam("category_id") long category , @RequestParam("order") String ord) {
        log.debug("REST request to get Ideas by catagory : {}");
        Category cat = categoryRepository.findOne(category);
        if(cat == null) {
            return ResponseEntity.badRequest().header("Failure", "This category does not exist").body(null);
        }
//        List<Idea> ideas = IdeaRepository.
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
