package com.informatorio.BlogPorject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatorio.BlogPorject.converter.ArticleConverter;
import com.informatorio.BlogPorject.converter.SourceConverter;
import com.informatorio.BlogPorject.dto.ArticleDTO;
import com.informatorio.BlogPorject.entity.ArticleEntity;
import com.informatorio.BlogPorject.repository.ArticleRepository;

@Service
public class ArticleService {
    
    private ArticleRepository articleRepository;
    private ArticleConverter articleConverter;
    private SourceConverter sourceConverter;
    //private SourceRepository sourceRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleConverter articleConverter, SourceConverter sourceConverter) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
        this.sourceConverter = sourceConverter;
        //this.sourceRepository = sourceRepository;
    }

    public ArticleDTO newArticle(ArticleDTO articleDTO) {
        ArticleEntity articleEntity = articleConverter.articleDTOToEntity(articleDTO);
        /*SourceEntity source = new SourceEntity();
        if (articleDTO.getSourceDTO() != null) {
            try {
                source = sourceRepository.findById(articleDTO.getSourceDTO().getId()).get();
            } catch (Exception e) {
                throw new NotFoundException("Not Found Source: " + articleDTO.getSourceDTO().getId());
            }
        }
        source = sourceRepository.findById(articleDTO.getSourceDTO().getId()).get();*/
        articleEntity = articleRepository.save(articleEntity);
        return articleConverter.articleEntityToDTO(articleEntity);

    }

    public List<ArticleDTO> getAllArticles() {
        List<ArticleEntity> entities = articleRepository.findAll();
        return articleConverter.toListDTO(entities);
    }

    public List<ArticleDTO> findBySomeWord(String word) {
        List<ArticleEntity> entities = articleRepository.findAll();
        List<ArticleEntity> entitiesFilter = new ArrayList<>();
        for (ArticleEntity author : entities) {
            if (author.getTitle().toLowerCase().contains(word.toLowerCase()) 
                || author.getDescription().toLowerCase().contains(word.toLowerCase())
                || author.getContent().toLowerCase().contains(word.toLowerCase())) {
                    entitiesFilter.add(author);
            }
        }
        return articleConverter.toListDTO(entitiesFilter);
    }

    public ArticleDTO modifyArticle(Long id, ArticleDTO articleDTO) {
        ArticleEntity entity = articleRepository.findById(id).orElse(null);
        entity.setTitle(articleDTO.getTitle());
        entity.setDescription(articleDTO.getDescription());
        entity.setUrl(articleDTO.getUrl());
        entity.setUrlToImage(articleDTO.getUrlToImage());
        entity.setPublishedAt(articleDTO.getPublishedAt());
        entity.setContent(articleDTO.getContent());
        entity.setSource(sourceConverter.sourceDTOToEntity(articleDTO.getSourceDTO()));
        entity = articleRepository.save(entity);
        return articleConverter.articleEntityToDTO(entity);
    }

    public String deleteAnArticle(Long id) {
        articleRepository.deleteById(id);
        return "El autor se borro correctamente";
    }
}
