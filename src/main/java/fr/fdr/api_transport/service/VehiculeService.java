package fr.fdr.api_transport.service;

import fr.fdr.api_transport.pojo.Vehicule;
import fr.fdr.api_transport.repository.ConducteurRepository;
import fr.fdr.api_transport.repository.VehiculeRepository;
import fr.fdr.api_transport.ws.ApiRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;

//    //décla et init d'une liste comportant des véhicules
//    private final List<Vehicule> vehiculesList = new ArrayList<>();
//
//    //mise en place d'un compteur qui sera utilisé pour positionner les id
//    private final AtomicLong counter = new AtomicLong(1);

    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    public Vehicule getVehiculeById(Long id) {
        return vehiculeRepository.findById(id).stream()
                .filter(vehicule -> vehicule.getVehiculeId().equals(id))
                .findAny()
                .orElse(null);
    }

    public Vehicule createVehicule(Vehicule vehicule) {
//        vehicule.setVehiculeId(counter.getAndIncrement());
//        vehiculesList.add(vehicule);
//        return vehicule;
        if(vehicule.getVehiculeId() == null) {
            return vehiculeRepository.save(vehicule);
        }else{
            return null;
        }


    }

    public void deleteVehiculeById(Long id) {
        //vehiculesList.removeIf(vehicule -> vehicule.getVehiculeId().equals(id));
        vehiculeRepository.deleteById(id);
    }


    public Vehicule getVehiculeByImmatriculation(String immatriculation) {
        return vehiculeRepository.findByImmatriculation(immatriculation);
    }

    public Vehicule updateVehicule( Long id, Vehicule vehicule) {
        Vehicule oldVehicule = this.getVehiculeById(id);
        if (oldVehicule != null) {
            oldVehicule.setImmatriculation(vehicule.getImmatriculation());
            oldVehicule.setAnnee(vehicule.getAnnee());
            oldVehicule.setModele(vehicule.getModele());
            oldVehicule.setMarque(vehicule.getMarque());
            this.vehiculeRepository.save(oldVehicule);
        }
        return oldVehicule;
    }
}
