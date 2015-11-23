package edu.sjsu.cmpe275.project.repository;

import edu.sjsu.cmpe275.project.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Comment entity.
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

}
