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

import com.informatorio.BlogPorject.converter.AuthorConverter;
import com.informatorio.BlogPorject.dto.AuthorDTO;
import com.informatorio.BlogPorject.entity.AuthorEntity;
import com.informatorio.BlogPorject.repository.AuthorRepository;

@RestController
public class AuthorController {
    
    private AuthorConverter authorConverter;
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorConverter authorConverter, AuthorRepository authorRepository) {
        this.authorConverter = authorConverter;
        this.authorRepository = authorRepository;
    }

    @PostMapping("/author")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorEntity authorEntity = authorConverter.authorDTOToEntity(authorDTO);
        authorEntity = authorRepository.save(authorEntity);
        return new ResponseEntity<AuthorDTO>(authorConverter.authorEntityToDTO(authorEntity), HttpStatus.CREATED);
    }

    @GetMapping("/author")
    public ResponseEntity<List<AuthorDTO>> getAll() {
        List<AuthorEntity> entities = authorRepository.findAll();
        return new ResponseEntity<List<AuthorDTO>>(authorConverter.toListDTO(entities),HttpStatus.OK);
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        AuthorEntity entity = authorRepository.findById(id).orElse(null);
        entity.setFirstName(authorDTO.getFirstName());
        entity.setLastName(authorDTO.getLastName());
        entity.setFullName(authorDTO.getFullName());
        entity.setCreatedAt(authorDTO.getCreatedAt());
        entity = authorRepository.save(entity);
        return new ResponseEntity<AuthorDTO>(authorConverter.authorEntityToDTO(entity), HttpStatus.OK);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
        return new ResponseEntity<String>("El autor se borro correctamente", HttpStatus.OK);
    }
}
