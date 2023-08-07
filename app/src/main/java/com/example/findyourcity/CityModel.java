package com.example.findyourcity;

import androidx.annotation.NonNull;

import java.util.Dictionary;
import java.util.Objects;

public class CityModel {
    //private int İD
    private long id;
    private String name;
    private String state;
    private String country;
    private CityCoord coord;

    public double getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CityCoord getCoord() {
        return coord;
    }

    public void setCoord(CityCoord coord) {
        this.coord = coord;
    }

    public CityModel(long id, String name, String state, String country, CityCoord coord) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.country = country;
        this.coord = coord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityModel cityModel = (CityModel) o;
        return id == cityModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //autotext'te paket adı yerine string şehir adı görünmesi için


    @NonNull
    @Override
    public String toString() {
        return "City: "+name;
    }
}

