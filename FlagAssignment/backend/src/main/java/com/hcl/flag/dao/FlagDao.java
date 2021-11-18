package com.hcl.flag.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.flag.models.Continent;
import com.hcl.flag.models.Country;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlagDao {

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Continent> loadFlags() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/continents.json"), new TypeReference<List<Continent>>(){});
    }
}
