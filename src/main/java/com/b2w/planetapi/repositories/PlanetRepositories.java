package com.b2w.planetapi.repositories;


import com.b2w.planetapi.entities.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepositories extends JpaRepository<Planet,Long> {

}
