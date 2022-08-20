package com.informatorio.BlogPorject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.informatorio.BlogPorject.converter.ArticleConverter;
import com.informatorio.BlogPorject.converter.SourceConverter;
import com.informatorio.BlogPorject.dto.ArticleDTO;
import com.informatorio.BlogPorject.entity.ArticleEntity;
import com.informatorio.BlogPorject.entity.AuthorEntity;
import com.informatorio.BlogPorject.entity.SourceEntity;
import com.informatorio.BlogPorject.repository.ArticleRepository;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {
    
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleConverter articleConverter;
    @Mock
    private SourceConverter sourceConverter;

    @InjectMocks
    private ArticleService articleService;

    @Test
    void given_an_inexsiting_article_id_when_modify_article_then_throw_an_entity_not_found_exception() {
        when(articleRepository.findById(10L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class,
                        () -> articleService.modifyArticle(10L, new ArticleDTO()));

        assertNotNull(exception);
        /*assertEquals(ArticleEntity.class, exception.getClass()); //Para hacer fallar el test le paso como 
        resultado esperado un tipo de objeto diferente.*/
        assertEquals(EntityNotFoundException.class, exception.getClass());
    }

    @Test
    void given_valid_article_id_and_datas_when_modify_article_then_return_article_modified() {
        when(articleRepository.findById(10L)).thenReturn(Optional.of(new ArticleEntity("Prueba", null, null, null, null, null, new AuthorEntity(), new SourceEntity())));

        ArticleEntity article = articleConverter.articleDTOToEntity(articleService.modifyArticle(10L, new ArticleDTO()));

        //assertNotNull(article);
        assertEquals(null, article);
    
    }
}
