package com.b2w.planetapi.resources;


import com.b2w.planetapi.entities.Planet;
import com.b2w.planetapi.repositories.PlanetRepositories;
import com.b2w.planetapi.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;

@Controller
@RequestMapping("/planet")
public class PlanetResource {

    @Autowired
    private PlanetService planetService;

    @PostMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Planet> insert(@RequestBody Planet planet){
        planet = planetService.save(planet);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(planet.getId()).toUri();
        return ResponseEntity.created(uri).body(planet);
    }
}
