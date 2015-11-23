package edu.sjsu.cmpe275.project.repository;

import edu.sjsu.cmpe275.project.domain.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Idea entity.
 */
public interface IdeaRepository extends JpaRepository<Idea,Long> {

}
