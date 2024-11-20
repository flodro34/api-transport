package fr.fdr.api_transport.pojo;

import lombok.Data;

@Data
public class Conducteur {

    private Long conducteurId;
    private String nom;
    private String prenom;
    private Long vehiculeId;


}
