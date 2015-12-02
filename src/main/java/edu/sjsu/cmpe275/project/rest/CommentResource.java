package edu.sjsu.cmpe275.project.rest;

import edu.sjsu.cmpe275.project.domain.Comment;
import edu.sjsu.cmpe275.project.domain.Idea;
import edu.sjsu.cmpe275.project.repository.CommentRepository;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.time.format.DateTimeFormatter;
/**
 * REST controller for managing Comment.
 */
@RestController
@RequestMapping("/api")
public class CommentResource {

    private final Logger log = LoggerFactory.getLogger(CommentResource.class);

    @Inject
    private CommentRepository commentRepository;

    @Inject
    private IdeaRepository ideaRepository;

    /**
     * POST  /ideas/{idea_id}/comments -> Create a new comment of an idea.
     */
    @RequestMapping(value = "/ideas/{idea_id}/comments",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Comment> createComment(@RequestBody Comment comment , @PathVariable("idea_id") long idea_id) throws URISyntaxException {
        log.debug("REST request to save Comment : {}", comment);

        if (comment.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new comment cannot already have an ID").body(null);
        }

        Idea idea = ideaRepository.findOne(idea_id);
        if(idea == null) {
            return ResponseEntity.badRequest().header("Failure", "Thia idea does not exist").body(null);
        }
        comment.setIdea(idea);
        comment.setTime(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        Comment result = commentRepository.save(comment);
        return ResponseEntity.created(new URI("/api/"+idea_id+"/comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("comment", result.getId().toString()))
            .body(result);
    }

//    /**
//     * PUT   /ideas/{idea_id}/comments -> Updates an existing comment of an idea.
//     */
//    @RequestMapping(value = "/ideas/{idea_id}/comments",
//        method = RequestMethod.PUT,
//        produces = MediaType.APPLICATION_JSON_VALUE)
//
//    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment , @PathVariable("idea_id") long idea_id) throws URISyntaxException {
//        log.debug("REST request to update Comment : {}", comment);
//        if (comment.getId() == null) {
//            return createComment(comment,idea_id);
//        }
//
//        Idea idea = ideaRepository.findOne(idea_id);
//
//        if(idea == null) {
//            return ResponseEntity.badRequest().header("Failure", "Thia idea does not exist").body(null);
//        }
//        comment.setIdea(idea);
//        Comment result = commentRepository.save(comment);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert("comment", comment.getId().toString()))
//            .body(result);
//    }

    /**
     * GET   /ideas/{idea_id}/comments -> get all the comments of an idea.
     */
    @RequestMapping(value = "/ideas/{idea_id}/comments",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Comment>> getAllComments(Pageable pageable , @PathVariable("idea_id") long idea_id)
        throws URISyntaxException {
//        Page<Comment> page = commentRepository.findAllByIdea_id(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/{idea_id}/comments");
//        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        Idea idea = ideaRepository.findOne(idea_id);
        log.debug("The idea is" + idea);
        if(idea == null) {
            return ResponseEntity.badRequest().header("Failure", "Thia idea does not exist").body(null);
        }
        List<Comment> list = commentRepository.findAllByIdea_id(idea);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/{idea_id}/comments");
        return Optional.ofNullable(list)
                .map(idea_comments -> new ResponseEntity<>(
                        list,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET   /ideas/{idea_id}/comments/:id -> get the "id" comment of an idea.
     */
    @RequestMapping(value = "/ideas/{idea_id}/comments/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Comment> getComment(@PathVariable("id") Long id , @PathVariable("idea_id") long idea_id) {
        log.debug("REST request to get Comment : {}", id);
        return Optional.ofNullable(commentRepository.findOne(id))
            .map(comment -> new ResponseEntity<>(
                comment,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /ideas/comments/:id -> delete the "id" comment.
     */
    @RequestMapping(value = "/ideas/{idea_id}/comments/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id , @PathVariable("idea_id") long idea_id) {
        log.debug("REST request to delete Comment : {}", id);
        commentRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("comment", id.toString())).build();
    }
}
