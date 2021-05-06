package com.b2w.planetapi.entities;

import java.util.List;

public class ResultApi {
    private List<StarWarsPlanet> results;

    public ResultApi() {
    }

    public ResultApi(List<StarWarsPlanet> results, String name) {
        this.results = results;
    }

    public List<StarWarsPlanet> getResults() {
        return results;
    }

    public void setResults(List<StarWarsPlanet> results) {
        this.results = results;
    }
}
