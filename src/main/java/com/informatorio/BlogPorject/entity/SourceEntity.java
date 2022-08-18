package com.informatorio.BlogPorject.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;*/ 
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sources")
public class SourceEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private LocalDate createdAt;

    //@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<ArticleEntity> articles = new ArrayList<>();

    //Constructors
    public SourceEntity() {}

    public SourceEntity(String name, LocalDate createdAt) {
        this.name = name;
        setCode(code);
        this.createdAt = createdAt;;
    }

    //Getters y Setters
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = name.replace(" ","-").toLowerCase();
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    //Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SourceEntity source = (SourceEntity) obj;
        return Objects.equals(id, source.id)
        && Objects.equals(name, source.name)
        && Objects.equals(code, source.code)
        && Objects.equals(createdAt, source.createdAt);
    }

    //HashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, createdAt);
    }

    //ToString
    @Override
    public String toString() {
        return "Source{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", birthDate='" + createdAt +
                '}';
    }
}
