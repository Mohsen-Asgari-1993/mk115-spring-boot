package ir.maktabsharif115.springboot.repository;

import ir.maktabsharif115.springboot.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @EntityGraph(attributePaths = {"roles", "roles.authorities"})
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
