package com.hcl.flag.controller;


import com.hcl.flag.models.Response;
import com.hcl.flag.services.FlagService;
import com.hcl.flag.services.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flags")
public class FlagController {

    @Autowired
    private FlagService flagService;

    @Autowired
    private MetricService metricService;

    @CrossOrigin(origins= "*")
    @GetMapping("")
    public ResponseEntity<Response> getAll(){
        return ResponseEntity.ok(new Response(flagService.getContinents()));
    }

    @CrossOrigin(origins= "*")
    @GetMapping("/{continent}")
    public ResponseEntity<Response> getCountries(@PathVariable String continent){
        return ResponseEntity.ok(new Response(flagService.getCountries(continent)));
    }

    @CrossOrigin(origins="*")
    @GetMapping("/{continent}/{country}")
    public ResponseEntity<Response> getFlag(@PathVariable String continent, @PathVariable String country) {
        return ResponseEntity.ok(new Response(flagService.getFlags(continent, Arrays.asList(country))));
    }

    @CrossOrigin(origins="*")
    @GetMapping("/metrics")
    public String getMetrics() {
        return metricService.getMetrics();
    }

}
