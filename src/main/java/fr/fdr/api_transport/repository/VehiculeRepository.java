package fr.fdr.api_transport.repository;

import fr.fdr.api_transport.pojo.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    Vehicule findByImmatriculation(String immatriculation);
}
