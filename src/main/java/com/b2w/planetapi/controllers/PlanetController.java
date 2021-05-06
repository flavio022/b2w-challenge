package com.b2w.planetapi.controllers;

import com.b2w.planetapi.entities.Planet;
import com.b2w.planetapi.repositories.PlanetRepositories;
import com.b2w.planetapi.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/planet")
public class PlanetController {

    @Autowired
    private PlanetRepositories planetRepositories;

    @Autowired
    private PlanetService planetService;

    @PostMapping
    public ResponseEntity<Planet> insert(@RequestBody Planet planet){
        planetRepositories.save(planetService.getAparicao(planet));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planet.getId()).toUri();

        return ResponseEntity.created(uri).body(planet);
    }

    @GetMapping
    public ResponseEntity listAll(@RequestParam(required = false) String planetName) {
        List<Planet> list;
        if(planetName==null){
            list = planetRepositories.listAll();
            return ResponseEntity.ok().body(list);
        }else{
           list = planetRepositories.findByName(planetName);
            return ResponseEntity.ok().body(list);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id){
        Planet planet = planetRepositories.findById(id);
        return ResponseEntity.ok().body(planet);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        planetRepositories.delete(id);
        return ResponseEntity.noContent().build();
   }


}
