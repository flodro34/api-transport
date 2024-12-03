package fr.fdr.api_transport.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehiculeId;
    private String marque;
    private String modele;
    private Integer annee;

    @Column(unique = true, nullable = false)
    private String immatriculation;

}
