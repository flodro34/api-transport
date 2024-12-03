package fr.fdr.api_transport.security.repository;


import fr.fdr.api_transport.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value= "SELECT u.* FROM users u " +
            "JOIN user_roles ur ON ur.user_id = u.id " +
            "JOIN roles r ON r.id = role_id " +
            "WHERE r.name = ?1",
            nativeQuery = true)
    List<User> findUserByRoles(String role);

    boolean existByUsername(String username);
}
