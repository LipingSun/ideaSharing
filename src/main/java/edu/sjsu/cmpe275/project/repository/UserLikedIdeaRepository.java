package edu.sjsu.cmpe275.project.repository;

import edu.sjsu.cmpe275.project.domain.UserLikedIdea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the UserLikedIdea entity.
 */
public interface UserLikedIdeaRepository extends JpaRepository<UserLikedIdea,Long> {

}
