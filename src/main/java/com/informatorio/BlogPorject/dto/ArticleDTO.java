package com.informatorio.BlogPorject.dto;

import java.time.LocalDate;
import java.util.Objects;

public class ArticleDTO {
    
    private Long id;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private LocalDate publishedAt;
    private String content;
    //private Long author;
    private SourceDTO source; 


    //Constructors
    public ArticleDTO() {}

    public ArticleDTO(Long id, String title, String description, String url, String urlToImage, LocalDate publishedAt, String content, SourceDTO source) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.source = source;
    }

    //Getters and Setters
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return this.urlToImage;
    }
    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public LocalDate getPublishedAt() {
        return this.publishedAt;
    }
    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public SourceDTO getSource() {
        return this.source;
    }
    public void setSource(SourceDTO source) {
        this.source = source;
    }

    //Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ArticleDTO article = (ArticleDTO) obj;
        return Objects.equals(id, article.id)
        && Objects.equals(title, article.title)
        && Objects.equals(description, article.description)
        && Objects.equals(url, article.url)
        && Objects.equals(urlToImage, article.urlToImage)
        && Objects.equals(publishedAt, article.publishedAt)
        && Objects.equals(content, article.content)
        && Objects.equals(source, article.source);
    }

    //HashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, url, urlToImage, publishedAt, content, source);
    }

    //ToString
    @Override
    public String toString() {
        return "Article{" +
                "Id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                ", source='" + source +
                '}';
    }
}
