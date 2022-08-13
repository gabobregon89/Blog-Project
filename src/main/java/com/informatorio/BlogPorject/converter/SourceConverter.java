package com.informatorio.BlogPorject.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.informatorio.BlogPorject.dto.SourceDTO;
import com.informatorio.BlogPorject.entity.SourceEntity;

@Component
public class SourceConverter {
    
    public SourceDTO sourceEntityToDTO(SourceEntity source) {
        return new SourceDTO(source.getId(),
                            source.getName(),
                            source.getCode(),
                            source.getCreatedAt());
    }

    public SourceEntity sourceDTOToEntity(SourceDTO dto) {
        return new SourceEntity(dto.getName(),
                                dto.getCreatedAt());
    }

    public List<SourceDTO> toListDTO(List<SourceEntity> sources) {
        return sources.stream()
                .map(source -> sourceEntityToDTO(source))
                .collect(Collectors.toList());
    }

    public List<SourceEntity> toListEntity(List<SourceDTO> sources) {
        return sources.stream()
                .map(source -> sourceDTOToEntity(source))
                .collect(Collectors.toList());
    }
}
