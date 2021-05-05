package com.b2w.planetapi.resources;


import com.b2w.planetapi.entities.Planet;
import com.b2w.planetapi.repositories.PlanetRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/planet")
public class PlanetResource {

    @Autowired
    private PlanetRepositories planetRepositories;

    @PostMapping
    public ResponseEntity<Planet> insert(@RequestBody Planet planet){
        planetRepositories.save(planet);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planet.getId()).toUri();
        return ResponseEntity.created(uri).body(planet);
    }
    @GetMapping
    public ResponseEntity<List<Planet>> listAll() {
        List<Planet> list = planetRepositories.listAll();
        return ResponseEntity.ok().body(list);
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
