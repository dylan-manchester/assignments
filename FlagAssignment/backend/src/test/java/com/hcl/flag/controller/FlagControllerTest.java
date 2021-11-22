package com.hcl.flag.controller;

import com.hcl.flag.services.FlagService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FlagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        String expected = "{\"content\":[\"Africa\",\"America\",\"Asia\",\"Europe\",\"Oceania\"]}";
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/flags").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(result.getResponse().getContentAsString(),expected);
    }

    @ParameterizedTest
    @CsvFileSource(files = {"src/test/resources/getCountriesValues.csv"})
    void getCountries(String continent, String expected) throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/flags/" + continent).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(result.getResponse().getContentAsString(), expected);
    }

    @ParameterizedTest
    @CsvFileSource(files = {"src/test/resources/getFlagValues.csv"})
    void getFlag(String continent, String country, String expected) throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/flags/"+continent+"/"+country).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(result.getResponse().getContentAsString(),expected);
    }
}