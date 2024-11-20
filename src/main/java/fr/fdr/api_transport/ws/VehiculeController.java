package fr.fdr.api_transport.ws;

import fr.fdr.api_transport.pojo.Vehicule;
import fr.fdr.api_transport.service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ApiRegistration.API + ApiRegistration.REST_VEHICULE)
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

    /**
     * retourne tous les véhicules
     * @return une liste de véhicules
     */
    @GetMapping
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    /**
     * retourne 1 seul véhicule
     * @param id
     * @return 1 véhicule
     */
    @GetMapping("/{id}")
    public Vehicule getVehiculeById(@PathVariable Long id) {
        return vehiculeService.getVehiculeById(id);
    }

    @PostMapping
    public Vehicule createVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.createVehicule(vehicule);
    }

    @DeleteMapping
    public void deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehiculeById(id);
    }

}
