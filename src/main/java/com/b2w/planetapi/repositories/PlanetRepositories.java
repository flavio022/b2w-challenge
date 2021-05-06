package com.b2w.planetapi.repositories;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.b2w.planetapi.entities.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Planet> findByName(String name) {
        return null;
    }
}
