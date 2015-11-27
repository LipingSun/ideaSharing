package edu.sjsu.cmpe275.project.repository;

import edu.sjsu.cmpe275.project.domain.Idea;
import edu.sjsu.cmpe275.project.domain.User;
import edu.sjsu.cmpe275.project.domain.UserLikedIdea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the UserLikedIdea entity. Chang's part
 */
public interface UserLikedIdeaRepository extends JpaRepository<UserLikedIdea, Long> {
    /**
     * Returns the UserLikedIdea entry list by using its user as search criteria.
     */
    List<UserLikedIdea> findByUser(User user);

    /**
     * Returns the UserLikedIdea entry list by using its idea as search criteria.
     */
    List<UserLikedIdea> findByIdea(Idea idea);
}
