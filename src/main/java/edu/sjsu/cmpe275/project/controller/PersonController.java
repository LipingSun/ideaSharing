package edu.sjsu.cmpe275.project.controller;

import edu.sjsu.cmpe275.project.dao.PersonDao;
import edu.sjsu.cmpe275.project.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/person*")
@Controller
public class PersonController {

    @Autowired
    private PersonDao personDao;


    /**
     * Get a person in JSON format
     * @param userId id of person
     * @return person in JSON format
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getPersonJSON(@PathVariable("id") long userId) {
        try {
            Person person = personDao.findById(userId);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

}