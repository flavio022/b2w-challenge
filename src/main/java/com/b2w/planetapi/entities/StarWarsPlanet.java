package com.b2w.planetapi.entities;
import java.util.List;
import java.util.Objects;

public class StarWarsPlanet {
    private String name;
    private List <String> films;

    public StarWarsPlanet() {
    }

    public StarWarsPlanet(String name, List<String> films) {
        this.name = name;
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarWarsPlanet that = (StarWarsPlanet) o;
        return Objects.equals(name, that.name) && Objects.equals(films, that.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, films);
    }
}
