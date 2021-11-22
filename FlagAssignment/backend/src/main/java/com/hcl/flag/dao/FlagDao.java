package com.hcl.flag.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.flag.models.Continent;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class FlagDao {

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Continent> loadFlags() throws IOException {
        return objectMapper.readValue(this.getClass().getClassLoader().getResourceAsStream("continents.json"), new TypeReference<List<Continent>>(){});
    }
}
