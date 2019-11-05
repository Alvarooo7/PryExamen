package com.example.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examen.entity.Authority;
@Repository
public interface AuthoryRepository extends JpaRepository<Authority, Long> {

}
