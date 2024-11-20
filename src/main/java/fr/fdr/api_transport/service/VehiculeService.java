package fr.fdr.api_transport.service;

import fr.fdr.api_transport.pojo.Vehicule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VehiculeService {

    //décla et init d'une liste comportant des véhicules
    private final List<Vehicule> vehiculesList = new ArrayList<>();

    //mise en place d'un compteur qui sera utilisé pour positionner les id
    private final AtomicLong counter = new AtomicLong(1);

    public List<Vehicule> getAllVehicules() {
        return vehiculesList;
    }


    public Vehicule getVehiculeById(Long id) {
        return vehiculesList.stream()
                .filter(vehicule -> vehicule.getVehiculeId().equals(id))
                .findAny()
                .orElse(null);
    }

    public Vehicule createVehicule(Vehicule vehicule) {
        vehicule.setVehiculeId(counter.getAndIncrement());
        vehiculesList.add(vehicule);
        return vehicule;
    }

    public void deleteVehiculeById(Long id) {
        vehiculesList.removeIf(vehicule -> vehicule.getVehiculeId().equals(id));
    }
}
