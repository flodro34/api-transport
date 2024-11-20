package fr.fdr.api_transport.pojo;

import lombok.Data;

@Data
public class Vehicule {

    private Long vehiculeId;
    private String marque;
    private String modele;
    private Integer annee;
    private String immatriculation;

}
