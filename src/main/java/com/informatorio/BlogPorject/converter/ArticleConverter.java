package com.informatorio.BlogPorject.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.informatorio.BlogPorject.dto.ArticleDTO;
import com.informatorio.BlogPorject.entity.ArticleEntity;
import com.informatorio.BlogPorject.entity.SourceEntity;
import com.informatorio.BlogPorject.repository.SourceRepository;

@Component
public class ArticleConverter {

    private SourceConverter sourceConverter;
    private SourceRepository sourceRepository;

    @Autowired
    public ArticleConverter(SourceConverter sourceConverter, SourceRepository sourceRepository) {
        this.sourceConverter = sourceConverter;
        this.sourceRepository = sourceRepository;
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
        SourceEntity source = sourceRepository.findById(dto.getSourceDTO().getId()).orElse(null);                      
        return new ArticleEntity(dto.getTitle(),
                                dto.getDescription(),
                                dto.getUrl(),
                                dto.getUrlToImage(),
                                dto.getPublishedAt(),
                                dto.getContent(),
                                source);
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
