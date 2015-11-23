package edu.sjsu.cmpe275.project.rest;

import edu.sjsu.cmpe275.project.domain.Person;
import edu.sjsu.cmpe275.project.repository.PersonRepository;
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
 * REST controller for managing Person.
 */
@RestController
@RequestMapping("/api")
public class PersonResource {

    private final Logger log = LoggerFactory.getLogger(PersonResource.class);

    @Inject
    private PersonRepository personRepository;

    /**
     * POST  /persons -> Create a new person.
     */
    @RequestMapping(value = "/persons",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) throws URISyntaxException {
        log.debug("REST request to save Person : {}", person);
        if (person.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new person cannot already have an ID").body(null);
        }
        Person result = personRepository.save(person);
        return ResponseEntity.created(new URI("/api/persons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("person", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /persons -> Updates an existing person.
     */
    @RequestMapping(value = "/persons",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person) throws URISyntaxException {
        log.debug("REST request to update Person : {}", person);
        if (person.getId() == null) {
            return createPerson(person);
        }
        Person result = personRepository.save(person);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("person", person.getId().toString()))
            .body(result);
    }

    /**
     * GET  /persons -> get all the persons.
     */
    @RequestMapping(value = "/persons",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Person>> getAllPersons(Pageable pageable)
        throws URISyntaxException {
        Page<Person> page = personRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/persons");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /persons/:id -> get the "id" person.
     */
    @RequestMapping(value = "/persons/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        log.debug("REST request to get Person : {}", id);
        return Optional.ofNullable(personRepository.findOne(id))
            .map(person -> new ResponseEntity<>(
                person,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /persons/:id -> delete the "id" person.
     */
    @RequestMapping(value = "/persons/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        log.debug("REST request to delete Person : {}", id);
        personRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("person", id.toString())).build();
    }
}
