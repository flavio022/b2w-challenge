package com.b2w.planetapi.services;

import com.b2w.planetapi.client.ApiClient;
import com.b2w.planetapi.entities.Planet;
import com.b2w.planetapi.entities.StarWarsPlanet;
import com.b2w.planetapi.repositories.PlanetRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PlanetService {

    @Autowired
    private ApiClient apiClient;

    @Autowired
    private PlanetRepositories planetRepositories;

    private List<StarWarsPlanet> result = new ArrayList<StarWarsPlanet>();
    private Calendar horaInicial = Calendar.getInstance();


    public Planet getAparicao(Planet planet) {
        this.result = apiClient.getAparicoes().getBody().getResults();
        return setAparicao(result,planet);
    }

    private Planet setAparicao(List<StarWarsPlanet> result, Planet planet) {
        for(StarWarsPlanet y: result ) {
            if(planet.getNome().equals(y.getName())) {
                planet.setAparicoes(y.getFilms().size());
                return planet;
            }
        }
        return planet;
    }

}
