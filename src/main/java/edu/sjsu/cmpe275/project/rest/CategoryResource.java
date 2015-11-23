package edu.sjsu.cmpe275.project.rest;

import edu.sjsu.cmpe275.project.domain.Category;
import edu.sjsu.cmpe275.project.repository.CategoryRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * REST controller for managing Category.
 */
@RestController
@RequestMapping("/api")
public class CategoryResource {

    @Inject
    private CategoryRepository categoryRepository;

    /**
     * GET  /categorys -> get all the categorys.
     */
    @RequestMapping(value = "/categorys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }

}
