package com.informatorio.BlogPorject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.informatorio.BlogPorject.dto.SourceDTO;
import com.informatorio.BlogPorject.service.SourceService;

@RestController
public class SourceController {

    private SourceService sourceService;

    @Autowired
    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }
    
    @PostMapping("/source")
    public ResponseEntity<SourceDTO> createSource(@RequestBody SourceDTO sourceDTO) {
        return new ResponseEntity<SourceDTO>(sourceService.newSource(sourceDTO), HttpStatus.CREATED);
    }

    @GetMapping("/source")
    public ResponseEntity<List<SourceDTO>> getAllSources() {
        return new ResponseEntity<List<SourceDTO>>(sourceService.getAllSources(), HttpStatus.OK);
    }

    @GetMapping("/source/{word}")
    public ResponseEntity<List<SourceDTO>> getByWord(@PathVariable String word) {
        return new ResponseEntity<List<SourceDTO>>(sourceService.findBySomeWord(word), HttpStatus.OK);
    }

    @PutMapping("/source/{id}")
    public ResponseEntity<SourceDTO> updateSource(@PathVariable Long id, @RequestBody SourceDTO sourceDTO) {
        return new ResponseEntity<SourceDTO>(sourceService.modifySource(id, sourceDTO), HttpStatus.OK);
    }

    @DeleteMapping("/source/{id}")
    public ResponseEntity<String> deleteSource(@PathVariable Long id) {
        return new ResponseEntity<String>(sourceService.deleteSource(id),HttpStatus.NO_CONTENT);
    }
}
