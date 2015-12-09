package edu.sjsu.cmpe275.project.repository;

import edu.sjsu.cmpe275.project.domain.Idea;
import edu.sjsu.cmpe275.project.domain.User;
import edu.sjsu.cmpe275.project.domain.UserReadIdea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the UserReadIdea entity. Chang's part
 */
@Repository
public interface UserReadIdeaRepository extends JpaRepository<UserReadIdea,Long> {
    /**
     * Returns the UserReadIdea entry list by using its user ID as search criteria.
     */
    List<UserReadIdea> findByUser(User user);

    /**
     * Returns the UserReadIdea entry list by using its idea ID as search criteria.
     */
    List<UserReadIdea> findByIdea(Idea idea);
}
