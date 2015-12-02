package edu.sjsu.cmpe275.project.repository;

import edu.sjsu.cmpe275.project.domain.Comment;
import edu.sjsu.cmpe275.project.domain.Idea;
import edu.sjsu.cmpe275.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Comment entity.
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("SELECT c FROM Comment c where c.idea = :idea")
    List<Comment> findAllByIdea_id(@Param("idea") Idea idea);
}
