package edu.sjsu.cmpe275.project.rest;

import edu.sjsu.cmpe275.project.domain.Comment;
import edu.sjsu.cmpe275.project.repository.CommentRepository;
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
 * REST controller for managing Comment.
 */
@RestController
@RequestMapping("/api")
public class CommentResource {

    private final Logger log = LoggerFactory.getLogger(CommentResource.class);

    @Inject
    private CommentRepository commentRepository;

    /**
     * POST  /comments -> Create a new comment.
     */
    @RequestMapping(value = "/comments",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) throws URISyntaxException {
        log.debug("REST request to save Comment : {}", comment);
        if (comment.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new comment cannot already have an ID").body(null);
        }
        Comment result = commentRepository.save(comment);
        return ResponseEntity.created(new URI("/api/comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("comment", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /comments -> Updates an existing comment.
     */
    @RequestMapping(value = "/comments",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) throws URISyntaxException {
        log.debug("REST request to update Comment : {}", comment);
        if (comment.getId() == null) {
            return createComment(comment);
        }
        Comment result = commentRepository.save(comment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("comment", comment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /comments -> get all the comments.
     */
    @RequestMapping(value = "/comments",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Comment>> getAllComments(Pageable pageable)
        throws URISyntaxException {
        Page<Comment> page = commentRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /comments/:id -> get the "id" comment.
     */
    @RequestMapping(value = "/comments/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        log.debug("REST request to get Comment : {}", id);
        return Optional.ofNullable(commentRepository.findOne(id))
            .map(comment -> new ResponseEntity<>(
                comment,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /comments/:id -> delete the "id" comment.
     */
    @RequestMapping(value = "/comments/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        log.debug("REST request to delete Comment : {}", id);
        commentRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("comment", id.toString())).build();
    }
}
