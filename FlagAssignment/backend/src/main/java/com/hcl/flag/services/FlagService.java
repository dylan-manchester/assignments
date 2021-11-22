package com.hcl.flag.services;


import com.hcl.flag.dao.FlagDao;
import com.hcl.flag.exception.ResourceNotFoundException;
import com.hcl.flag.models.Continent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlagService {

    @Autowired
    private FlagDao flagDao;

    @Autowired
    private MetricService metricService;

    public List<String> getContinents() {
        try {
            List<Continent> data = flagDao.loadFlags();
            List<String> response = data.stream().map(continent -> continent.getContinent()).collect(Collectors.toList());
            metricService.collectMetric("Total");
            return response;
        } catch (IOException e) {
            throw new ResourceNotFoundException(e);
        }
    }

    public List<String> getCountries(String continent){
        try {
            List<Continent> data = flagDao.loadFlags();
            Continent c = data.stream().filter(cont -> cont.getContinent().equals(continent)).findFirst().orElseThrow(ResourceNotFoundException::new);
            List<String> response = c.getCountries().stream().map(country -> country.getName()).collect(Collectors.toList());
            metricService.collectMetric("Total");
            metricService.collectMetric(continent);
            return response;
        } catch (IOException e) {
            throw new ResourceNotFoundException(e);
        }

    }

    public List<String> getFlags(String continent, List<String> countries){
        try {
            List<Continent> data = flagDao.loadFlags();
            Continent c = data.stream().filter(cont -> cont.getContinent().equals(continent)).findFirst().orElseThrow(ResourceNotFoundException::new);
            List<String> response = c.getCountries().stream().filter(country -> countries.contains(country.getName())).map(country -> country.getFlag()).collect(Collectors.toList());
            countries.forEach((country)->{
                metricService.collectMetric("Total");
                metricService.collectMetric(continent);
                metricService.collectMetric(country);
            });
            return response;
        } catch (IOException e) {
            throw new ResourceNotFoundException(e);
        }
    }
}
