package com.informatorio.BlogPorject.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatorio.BlogPorject.converter.AuthorConverter;
import com.informatorio.BlogPorject.dto.AuthorDTO;
import com.informatorio.BlogPorject.entity.AuthorEntity;
import com.informatorio.BlogPorject.repository.AuthorRepository;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private AuthorConverter authorConverter;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorConverter authorConverter) {
        this.authorRepository = authorRepository;
        this.authorConverter = authorConverter;
    }

    public AuthorDTO newAuthor(AuthorDTO authorDTO) {
        AuthorEntity authorEntity = authorConverter.authorDTOToEntity(authorDTO);
        authorEntity = authorRepository.save(authorEntity);
        return authorConverter.authorEntityToDTO(authorEntity);
    }

    public List<AuthorDTO> getAuthors() {
        List<AuthorEntity> entities = authorRepository.findAll();
        return authorConverter.toListDTO(entities);
    }

    public List<AuthorDTO> findBySomeWord(String some) {
        List<AuthorEntity> entities = authorRepository.findAll();
        List<AuthorEntity> entitiesFilter = new ArrayList<>();
        /*entities.stream()
                .filter(author -> author.getFullName().contains(some))
                .collect(Collectors.toList());*/
        for (AuthorEntity author : entities) {
            if (author.getFullName().toLowerCase().contains(some.toLowerCase())) {
                entitiesFilter.add(author);
            }
        }
        return authorConverter.toListDTO(entitiesFilter);
    }

    
    public List<AuthorDTO> compareByDate(String date) {
        List<AuthorEntity> entities = authorRepository.findAll();
        List<AuthorEntity> entitiesFilter = new ArrayList<>();
        LocalDate fecha = LocalDate.parse(date);
        for (AuthorEntity author : entities) {
            if (author.getCreatedAt().compareTo(fecha) > 0) {
                entitiesFilter.add(author);
            }
        }
        return authorConverter.toListDTO(entitiesFilter);
    }

    public AuthorDTO modifyAuthor(Long id, AuthorDTO authorDTO) {
        AuthorEntity entity = authorRepository.findById(id).orElse(null);
        entity.setFirstName(authorDTO.getFirstName());
        entity.setLastName(authorDTO.getLastName());
        entity.setFullName();
        entity.setCreatedAt(authorDTO.getCreatedAt());
        entity = authorRepository.save(entity);
        return authorConverter.authorEntityToDTO(entity);
    }

    public String deleteAnAuthor(Long id) {
        authorRepository.deleteById(id);
        return "El autor se borro correctamente";
    }
}