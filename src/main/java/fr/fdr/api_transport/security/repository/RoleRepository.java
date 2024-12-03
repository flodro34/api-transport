package fr.fdr.api_transport.security.repository;

import fr.fdr.api_transport.security.models.ERole;
import fr.fdr.api_transport.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
