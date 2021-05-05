package com.b2w.planetapi.repositories;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.b2w.planetapi.entities.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanetRepositories {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Planet save(Planet planet) {
        dynamoDBMapper.save(planet);
        return planet;
    }
    public List<Planet> listAll(){
        List<Planet> planets = dynamoDBMapper.scan(Planet.class,new DynamoDBScanExpression());
        return planets;
    }

    public Planet findById(String id) {
        Planet planet = dynamoDBMapper.load(Planet.class,id);
        return planet;
    }


    public void delete(String id) {
        Planet planet = findById(id);
        dynamoDBMapper.delete(planet);

    }
}
