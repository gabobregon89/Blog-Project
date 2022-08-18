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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "authors")
public class AuthorEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private LocalDate createdAt;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<ArticleEntity> articles = new ArrayList<>();

    //Constructors
    public AuthorEntity() {}

    /*public AuthorEntity(String firstName, String lastName, String fullName, LocalDate createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.createdAt = createdAt;
    }*/
    public AuthorEntity(String firstName, String lastName, LocalDate createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        setFullName(fullName);
        this.createdAt = createdAt;
    }

    //Getters and Setters
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = this.firstName + ' ' + this.lastName;
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
        AuthorEntity author = (AuthorEntity) obj;
        return Objects.equals(id, author.id)
        && Objects.equals(firstName, author.firstName)
        && Objects.equals(lastName, author.lastName)
        && Objects.equals(fullName, author.fullName)
        && Objects.equals(createdAt, author.createdAt);
    }

    //HashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, fullName, createdAt);
    }

    //ToString
    @Override
    public String toString() {
        return "Author{" +
                "Id=" + id +
                ", firstname='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDate='" + createdAt +
                '}';
    }
}
