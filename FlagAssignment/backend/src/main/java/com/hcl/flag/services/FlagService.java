package com.hcl.flag.services;


import com.hcl.flag.dao.FlagDao;
import com.hcl.flag.exception.ResourceNotFoundException;
import com.hcl.flag.models.Continent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlagService {

    @Autowired
    private FlagDao flagDao;

    public List<String> getContinents() {
        try {
            List<Continent> data = flagDao.loadFlags();
            return data.stream().map(continent -> continent.getContinent()).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ResourceNotFoundException(e);
        }
    }

    public List<String> getCountries(String continent){
        try {
            List<Continent> data = flagDao.loadFlags();
            Continent c = data.stream().filter(cont -> cont.getContinent().equals(continent)).findFirst().orElseThrow(ResourceNotFoundException::new);
            return c.getCountries().stream().map(country -> country.getName()).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ResourceNotFoundException(e);
        }

    }

    public List<String> getFlags(String continent, List<String> countries){
        try {
            List<Continent> data = flagDao.loadFlags();
            Continent c = data.stream().filter(cont -> cont.getContinent().equals(continent)).findFirst().orElseThrow(ResourceNotFoundException::new);
            return c.getCountries().stream().filter(country -> countries.contains(country.getName())).map(country -> country.getFlag()).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ResourceNotFoundException(e);
        }
    }
}
