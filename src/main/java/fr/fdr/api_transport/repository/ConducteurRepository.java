package fr.fdr.api_transport.repository;

import fr.fdr.api_transport.pojo.Conducteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConducteurRepository extends JpaRepository<Conducteur, Long> {
}
