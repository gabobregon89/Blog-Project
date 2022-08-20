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

import com.informatorio.BlogPorject.dto.ArticleDTO;
import com.informatorio.BlogPorject.service.ArticleService;

@RestController
public class ArticleController {
    
    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/article")
    public ResponseEntity<ArticleDTO> createAuthor(@RequestBody ArticleDTO articleDTO) {
        return new ResponseEntity<ArticleDTO>(articleService.newArticle(articleDTO),
                                            HttpStatus.CREATED);
    }

    @GetMapping("/article")
    public ResponseEntity<List<ArticleDTO>> getAll() {
        return new ResponseEntity<List<ArticleDTO>>(articleService.getAllArticles(),
                                                HttpStatus.OK);
    }

    @GetMapping("/article/{some}")
    public ResponseEntity<List<ArticleDTO>> getBySomeWord(@PathVariable String some) {
        return new ResponseEntity<List<ArticleDTO>>(articleService.findBySomeWord(some),
                                                HttpStatus.OK);
    }

    @GetMapping("/articledraft")
    public ResponseEntity<List<ArticleDTO>> getArticleDraft() {
        return new ResponseEntity<List<ArticleDTO>>(articleService.getArticleDraft(),
                                                HttpStatus.OK);
    }

    @PutMapping("/article/{id}")
    public ResponseEntity<ArticleDTO> updateAuthor(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        return new ResponseEntity<ArticleDTO>(articleService.modifyArticle(id, articleDTO),
                                            HttpStatus.OK);
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        return new ResponseEntity<String>(articleService.deleteAnArticle(id),
                                        HttpStatus.NO_CONTENT);
    }
}
