package edu.sjsu.cmpe275.project.repository;

import edu.sjsu.cmpe275.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Person entity.
 */
public interface UserRepository extends JpaRepository<User,Long> {

}
