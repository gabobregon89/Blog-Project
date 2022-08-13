package com.informatorio.BlogPorject.dto;

import java.time.LocalDate;
import java.util.Objects;

public class SourceDTO {
    
    private Long id;
    private String name;
    private String code;
    private LocalDate createdAt;

    //Constructors
    public SourceDTO() {}

    public SourceDTO(Long id, String name, String code, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createdAt = createdAt;
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
        this.code = code;
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
        SourceDTO source = (SourceDTO) obj;
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
