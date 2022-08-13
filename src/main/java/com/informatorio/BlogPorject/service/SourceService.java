package com.informatorio.BlogPorject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatorio.BlogPorject.converter.SourceConverter;
import com.informatorio.BlogPorject.dto.SourceDTO;
import com.informatorio.BlogPorject.entity.SourceEntity;
import com.informatorio.BlogPorject.repository.SourceRepository;

@Service
public class SourceService {
    
    private SourceRepository sourceRepository;
    private SourceConverter sourceConverter;

    @Autowired
    public SourceService(SourceRepository sourceRepository, SourceConverter sourceConverter) {
        this.sourceRepository = sourceRepository;
        this.sourceConverter = sourceConverter;
    }

    public SourceDTO newSource(SourceDTO sourceDTO) {
        SourceEntity sourceEntity = sourceConverter.sourceDTOToEntity(sourceDTO);
        sourceEntity = sourceRepository.save(sourceEntity);
        return sourceConverter.sourceEntityToDTO(sourceEntity);
    }

    public List<SourceDTO> getAllSources() {
        List<SourceEntity> entities = sourceRepository.findAll();
        return sourceConverter.toListDTO(entities);
    }

    public List<SourceDTO> findBySomeWord(String word) {
        List<SourceEntity> entities = sourceRepository.findAll();
        List<SourceEntity> entitiesFilter = new ArrayList<>();
        for (SourceEntity source : entities) {
            if (source.getName().toLowerCase().contains(word.toLowerCase())) {
                entitiesFilter.add(source);
            }
        }
        return sourceConverter.toListDTO(entitiesFilter);
    }

    public SourceDTO modifySource(Long id, SourceDTO sourceDTO) {
        SourceEntity entity = sourceRepository.findById(id).orElse(null);
        entity.setName(sourceDTO.getName());
        entity.setCode(sourceDTO.getCode());
        entity.setCreatedAt(sourceDTO.getCreatedAt());
        entity = sourceRepository.save(entity);
        return sourceConverter.sourceEntityToDTO(entity);
    }

    public String deleteSource(Long id) {
        sourceRepository.deleteById(id);
        return "El autor se borro correctamente";
    }
}
