package com.informatorio.BlogPorject.dto;

import java.time.LocalDate;
import java.util.Objects;

public class AuthorDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private LocalDate createdAt;

    //Constructors
    public AuthorDTO() {}

    public AuthorDTO(Long id, String firstName, String lastName, String fullName, LocalDate createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
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
        this.fullName = fullName;
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
        AuthorDTO authorDTO = (AuthorDTO) obj;
        return Objects.equals(id, authorDTO.id)
        && Objects.equals(firstName, authorDTO.firstName)
        && Objects.equals(lastName, authorDTO.lastName)
        && Objects.equals(fullName, authorDTO.fullName)
        && Objects.equals(createdAt, authorDTO.createdAt);
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
