package edu.sjsu.cmpe275.project.repository;

import edu.sjsu.cmpe275.project.domain.UserReadIdea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the UserReadIdea entity.
 */
public interface UserReadIdeaRepository extends JpaRepository<UserReadIdea,Long> {

}
