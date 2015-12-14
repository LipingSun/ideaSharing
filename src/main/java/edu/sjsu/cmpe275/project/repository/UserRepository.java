package edu.sjsu.cmpe275.project.repository;

import edu.sjsu.cmpe275.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Person entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findOneByUsername(String username);
}
