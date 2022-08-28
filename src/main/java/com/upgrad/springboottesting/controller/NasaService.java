package com.upgrad.springboottesting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NasaService {
    private RestTemplate restTemplate;

    // Mock - mocks all code/methods
    // Spy - Only mock few method, partial mock

    @Autowired
    public NasaService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    // Fetch data from rest API
    public byte[] search(String key) throws JsonProcessingException {
        String json = restTemplate.getForObject("https://images-api.nasa.gov/search?q=" + key, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readValue(json, JsonNode.class);
        String url = node.get("collection").get("items").get(0).get("links").get(0).get("href").asText();
        return restTemplate.getForObject(url, byte[].class);
    }

    // Fetch data from database
    public String readFromDatabase(Long id){
        // connect to database
        // make a query
        // return a result
        return "result from database";
    }

    // Does some logic. DOES NOT connect to db/api
    public String filter(){
        // Not connecting to database - service
        return  "result";
    }
}
