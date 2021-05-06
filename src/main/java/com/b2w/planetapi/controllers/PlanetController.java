package com.b2w.planetapi.resources;

import com.b2w.planetapi.client.ApiClient;
import com.b2w.planetapi.entities.Planet;
import com.b2w.planetapi.entities.StarWarsPlanet;
import com.b2w.planetapi.repositories.PlanetRepositories;
import com.b2w.planetapi.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/planet")
public class PlanetResource {

    @Autowired
    private ApiClient apiClient;

    @Autowired
    private PlanetRepositories planetRepositories;
    @Autowired
    private PlanetService planetService;

    private List<StarWarsPlanet> result = new ArrayList<StarWarsPlanet>();
    private Calendar horaInicial = Calendar.getInstance();

    @PostMapping
    public ResponseEntity<Planet> insert(@RequestBody Planet planet){
        planetRepositories.save(planet);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planet.getId()).toUri();
        insereAparicao(planetRepositories.listAll());
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

    public List<Planet> insereAparicao(List<Planet> planetas) {
        List<Planet> resposta = new ArrayList<>();
        this.result =  implementaCache(this.result, horaInicial);
        for(Planet x: planetas ) {
            x.setAparicoes(2);
        }
        return resposta;
    }

    private int encontraAparicao(List<StarWarsPlanet> result,Planet planeta) {
        for(StarWarsPlanet y: result ) {
            if(planeta.getNome().equals(y.getName())) {
                return y.getFilms().size();
            }
        }
        return 0;
    }
    private List<StarWarsPlanet> implementaCache(List<StarWarsPlanet> result, Calendar horaInicial) {
        Calendar atual = Calendar.getInstance();
        Calendar horaComparar = (Calendar) horaInicial.clone();
        horaComparar.add(Calendar.HOUR_OF_DAY, 1);
        if(result.isEmpty()) {
            result = apiClient.RetornaAparicoes().getBody().getResults();
        }
        if(atual.after(horaComparar)) {
            result = apiClient.RetornaAparicoes().getBody().getResults();
            horaInicial = Calendar.getInstance();
        }
        return result;
    }
}
