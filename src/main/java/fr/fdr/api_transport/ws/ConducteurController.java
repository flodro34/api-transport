package fr.fdr.api_transport.ws;

import fr.fdr.api_transport.pojo.Conducteur;
import fr.fdr.api_transport.service.ConducteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ConducteurController {

    @Autowired
    private ConducteurService conducteurService;

    @GetMapping
    public List<Conducteur> getConducteurs() {
        return conducteurService.getAllConducteur();
    }

    @GetMapping("/{id}")
    public Conducteur getConducteur(@PathVariable Long id) {
        return conducteurService.getConducteurById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteConducteur(@PathVariable Long id) {
        conducteurService.deleteConducteurById(id);
    }

    @PostMapping
    public Conducteur createConducteur(@RequestBody Conducteur conducteur) {
        return conducteurService.createConducteur(conducteur);
    }

}
