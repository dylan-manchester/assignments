package com.hcl.flag.controller;


import com.hcl.flag.models.Response;
import com.hcl.flag.services.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/flags")
public class FlagController {

    @Autowired
    private FlagService flagService;

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

    @CrossOrigin(origins= "*")
    @PostMapping("/{continent}")
    public ResponseEntity<Response> getFlags(@PathVariable String continent, @RequestBody List<String> countries) {
        return ResponseEntity.ok(new Response(flagService.getFlags(continent, countries)));
    }

    @CrossOrigin(origins="*")
    @GetMapping("/{continent}/{country}")
    public ResponseEntity<Response> getFlag(@PathVariable String continent, @PathVariable String country) {
        return ResponseEntity.ok(new Response(flagService.getFlags(continent, Arrays.asList(country))));
    }
}
