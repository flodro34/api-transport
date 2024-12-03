package fr.fdr.api_transport.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Conducteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conducteurId;
    private String nom;
    private String prenom;
    private Long vehiculeId;


}
