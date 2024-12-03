package fr.fdr.api_transport.service;

import fr.fdr.api_transport.pojo.Conducteur;
import fr.fdr.api_transport.pojo.Vehicule;
import fr.fdr.api_transport.repository.ConducteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ConducteurService {

//    //décla et init d'une liste comportant des conducteurs
//    private final List<Conducteur> conducteurList = new ArrayList<>();
//
//    //mise en place d'un compteur qui sera utilisé pour positionner les id
//    private final AtomicLong counter = new AtomicLong(1);

    @Autowired
    private ConducteurRepository conducteurRepository;

    public List<Conducteur> getAllConducteur() {
        return conducteurRepository.findAll();
    }

    public Conducteur getConducteurById(Long id) {
        return conducteurRepository.findById(id).stream()
                .filter(conducteur -> conducteur.getConducteurId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteConducteurById(long id) {
        //conducteurList.removeIf(conducteur -> conducteur.getConducteurId().equals(id));
        conducteurRepository.deleteById(id);
    }

    public Conducteur createConducteur(Conducteur conducteur) {
//        conducteur.setConducteurId(counter.getAndIncrement());
//        conducteurList.add(conducteur);
//        return conducteur;
        if (conducteur.getConducteurId() == null) {
            return conducteurRepository.save(conducteur);
        }else{
            return null;
        }

    }

    public Conducteur updateConducteur(Long id, Conducteur conducteur) {
        Conducteur oldConducteur = this.getConducteurById(id);
        if (oldConducteur != null) {
            oldConducteur.setNom(conducteur.getNom());
            oldConducteur.setPrenom(conducteur.getPrenom());
            oldConducteur.setVehiculeId(conducteur.getVehiculeId());
            return conducteurRepository.save(oldConducteur);
        }
        return oldConducteur;
    }
}
