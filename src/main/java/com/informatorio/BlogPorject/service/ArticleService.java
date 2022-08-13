package com.informatorio.BlogPorject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatorio.BlogPorject.converter.ArticleConverter;
import com.informatorio.BlogPorject.dto.ArticleDTO;
import com.informatorio.BlogPorject.entity.ArticleEntity;
import com.informatorio.BlogPorject.repository.ArticleRepository;

@Service
public class ArticleService {
    
    private ArticleRepository articleRepository;
    private ArticleConverter articleConverter ;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleConverter articleConverter) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
    }

    public ArticleDTO newArticle(ArticleDTO articleDTO) {
        ArticleEntity articleEntity = articleConverter.articleDTOToEntity(articleDTO);
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

    /*public List<ArticleDTO> compareByDate(String date) {
        List<ArticleEntity> entities = authorRepository.findAll();
        List<ArticleEntity> entitiesFilter = new ArrayList<>();
        LocalDate fecha = LocalDate.parse(date);
        for (ArticleEntity author : entities) {
            if (author.getCreatedAt().compareTo(fecha) > 0) {
                entitiesFilter.add(author);
            }
        }
        return authorConverter.toListDTO(entitiesFilter);
    }*/

    public ArticleDTO modifyArticle(Long id, ArticleDTO articleDTO) {
        ArticleEntity entity = articleRepository.findById(id).orElse(null);
        entity.setTitle(articleDTO.getTitle());
        entity.setDescription(articleDTO.getDescription());
        entity.setUrl(articleDTO.getUrl());
        entity.setUrlToImage(articleDTO.getUrlToImage());
        entity.setPublishedAt(articleDTO.getPublishedAt());
        entity.setContent(articleDTO.getContent());
        entity = articleRepository.save(entity);
        return articleConverter.articleEntityToDTO(entity);
    }

    public String deleteAnArticle(Long id) {
        articleRepository.deleteById(id);
        return "El autor se borro correctamente";
    }
}
