package com.informatorio.BlogPorject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informatorio.BlogPorject.entity.SourceEntity;

@Repository
public interface SourceRepository extends JpaRepository<SourceEntity, Long>{
    
}
