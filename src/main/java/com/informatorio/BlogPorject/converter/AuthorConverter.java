package com.informatorio.BlogPorject.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.informatorio.BlogPorject.dto.AuthorDTO;
import com.informatorio.BlogPorject.entity.AuthorEntity;

@Component
public class AuthorConverter {
    
    public AuthorDTO authorEntityToDTO(AuthorEntity author) {
        return new AuthorDTO(author.getId(),
                            author.getFirstName(),
                            author.getLastName(),
                            author.getFullName(),
                            author.getCreatedAt());
    }

    public AuthorEntity authorDTOToEntity(AuthorDTO dto) {
        return new AuthorEntity(dto.getFirstName(),
                                dto.getLastName(),
                                dto.getCreatedAt());
    }

    public List<AuthorDTO> toListDTO(List<AuthorEntity> authors) {
        return authors.stream()
                .map(author -> authorEntityToDTO(author))
                .collect(Collectors.toList());
    }

    public List<AuthorEntity> toListEntity(List<AuthorDTO> authors) {
        return authors.stream()
                .map(author -> authorDTOToEntity(author))
                .collect(Collectors.toList());
    }
}
