package edu.sjsu.cmpe275.project.repository;

import edu.sjsu.cmpe275.project.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Category entity.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
