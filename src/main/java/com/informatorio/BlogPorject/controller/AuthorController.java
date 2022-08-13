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

import com.informatorio.BlogPorject.dto.AuthorDTO;
import com.informatorio.BlogPorject.service.AuthorService;

@RestController
public class AuthorController {
    
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/author")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<AuthorDTO>(authorService.newAuthor(authorDTO),
                                            HttpStatus.CREATED);
    }

    @GetMapping("/author")
    public ResponseEntity<List<AuthorDTO>> getAll() {
        return new ResponseEntity<List<AuthorDTO>>(authorService.getAuthors(),
                                                HttpStatus.OK);
    }

    @GetMapping("/author/{some}")
    public ResponseEntity<List<AuthorDTO>> getBySomeWord(@PathVariable String some) {
        return new ResponseEntity<List<AuthorDTO>>(authorService.findBySomeWord(some),
                                                HttpStatus.OK);
    }

    @GetMapping("/authordate/{date}")
    public ResponseEntity<List<AuthorDTO>> getByDate(@PathVariable String date) {
        return new ResponseEntity<List<AuthorDTO>>(authorService.compareByDate(date),
                                                HttpStatus.OK);
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<AuthorDTO>(authorService.modifyAuthor(id, authorDTO),
                                            HttpStatus.OK);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        return new ResponseEntity<String>(authorService.deleteAnAuthor(id),
                                        HttpStatus.NO_CONTENT);
    }
}
