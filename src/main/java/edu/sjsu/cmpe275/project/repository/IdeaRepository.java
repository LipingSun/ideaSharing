package edu.sjsu.cmpe275.project.repository;

import edu.sjsu.cmpe275.project.domain.Category;
import edu.sjsu.cmpe275.project.domain.Idea;
import edu.sjsu.cmpe275.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Idea entity.
 */
@Repository
public interface IdeaRepository extends JpaRepository<Idea,Long> {

    @Query("SELECT i FROM Idea i where i.user = :user")
    List<Idea> findAllByUser_id(@Param("user") User user);

//    @Query("SELECT i FROM Idea i where i.category = :category group by  ")
//    List<Idea> findAllByCategory_id(@Param("category") Category category , @Param("ord") String ord);
}
