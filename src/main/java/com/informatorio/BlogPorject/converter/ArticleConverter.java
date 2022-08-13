package com.informatorio.BlogPorject.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.informatorio.BlogPorject.dto.ArticleDTO;
import com.informatorio.BlogPorject.entity.ArticleEntity;

@Component
public class ArticleConverter {

    private SourceConverter sourceConverter;

    @Autowired
    public ArticleConverter(SourceConverter sourceConverter) {
        this.sourceConverter = sourceConverter;
    }
    
    public ArticleDTO articleEntityToDTO(ArticleEntity article) {
        return new ArticleDTO(article.getId(),
                            article.getTitle(),
                            article.getDescription(),
                            article.getUrl(),
                            article.getUrlToImage(),
                            article.getPublishedAt(),
                            article.getContent(),
                            sourceConverter.sourceEntityToDTO(article.getSource()));
    }

    public ArticleEntity articleDTOToEntity(ArticleDTO dto) {
        return new ArticleEntity(dto.getTitle(),
                                dto.getDescription(),
                                dto.getUrl(),
                                dto.getUrlToImage(),
                                dto.getPublishedAt(),
                                dto.getContent(),
                                sourceConverter.sourceDTOToEntity(dto.getSource()));
    }

    public List<ArticleDTO> toListDTO(List<ArticleEntity> articles) {
        return articles.stream()
                .map(article -> articleEntityToDTO(article))
                .collect(Collectors.toList());
    }

    public List<ArticleEntity> toListEntity(List<ArticleDTO> articles) {
        return articles.stream()
                .map(article -> articleDTOToEntity(article))
                .collect(Collectors.toList());
    }
}
