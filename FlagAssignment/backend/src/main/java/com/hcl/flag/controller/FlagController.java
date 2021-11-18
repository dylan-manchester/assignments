package com.hcl.flag.controller;


import com.hcl.flag.services.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/flags")
public class FlagController {

    @Autowired
    private FlagService flagService;

    @GetMapping("")
    public ResponseEntity<List<String>> getAll(){
        return ResponseEntity.ok(flagService.getContinents());
    }

    @GetMapping("/{continent}")
    public ResponseEntity<List<String>> getCountries(@PathVariable String continent){
        return ResponseEntity.ok(flagService.getCountries(continent));
    }

    @PostMapping("/{continent}")
    public ResponseEntity<List<String>> getFlags(@PathVariable String continent, @RequestBody List<String> countries) {
        return ResponseEntity.ok(flagService.getFlags(continent, countries));
    }
}
