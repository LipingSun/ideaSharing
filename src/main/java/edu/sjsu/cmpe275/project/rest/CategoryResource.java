package edu.sjsu.cmpe275.project.rest;

import edu.sjsu.cmpe275.project.domain.Category;
import edu.sjsu.cmpe275.project.repository.CategoryRepository;
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
 * REST controller for managing Category.
 */
@RestController
@RequestMapping("/api")
public class CategoryResource {

    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    @Inject
    private CategoryRepository categoryRepository;

    /**
     * POST  /categorys -> Create a new category.
     */
    @RequestMapping(value = "/categorys",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws URISyntaxException {
        log.debug("REST request to save Category : {}", category);
        if (category.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new category cannot already have an ID").body(null);
        }
        Category result = categoryRepository.save(category);
        return ResponseEntity.created(new URI("/api/categorys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("category", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /categorys -> Updates an existing category.
     */
    @RequestMapping(value = "/categorys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws URISyntaxException {
        log.debug("REST request to update Category : {}", category);
        if (category.getId() == null) {
            return createCategory(category);
        }
        Category result = categoryRepository.save(category);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("category", category.getId().toString()))
            .body(result);
    }

    /**
     * GET  /categorys -> get all the categorys.
     */
    @RequestMapping(value = "/categorys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public List<Category> getAllCategorys() {
        log.debug("REST request to get all Categorys");
        return categoryRepository.findAll();
    }

    /**
     * GET  /categorys/:id -> get the "id" category.
     */
    @RequestMapping(value = "/categorys/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        log.debug("REST request to get Category : {}", id);
        return Optional.ofNullable(categoryRepository.findOne(id))
            .map(category -> new ResponseEntity<>(
                category,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /categorys/:id -> delete the "id" category.
     */
    @RequestMapping(value = "/categorys/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.debug("REST request to delete Category : {}", id);
        categoryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("category", id.toString())).build();
    }
}
