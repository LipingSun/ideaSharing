package edu.sjsu.cmpe275.project.repository;

import edu.sjsu.cmpe275.project.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Person entity.
 */
public interface PersonRepository extends JpaRepository<Person,Long> {

}
