package com.b2w.planetapi.services;

import com.b2w.planetapi.entities.Planet;
import com.b2w.planetapi.repositories.PlanetRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {
    @Autowired
    private PlanetRepositories planetRepositories;


}
