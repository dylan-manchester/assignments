package com.hcl.flag.models;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class Continent {
    private String continent;
    private List<Country> countries;

    public String getContinent() {
        return continent;
    }

    public void setContinent(String name) {
        this.continent = name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
