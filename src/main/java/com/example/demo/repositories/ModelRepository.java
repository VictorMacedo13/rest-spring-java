package com.example.demo.repositories;

import com.example.demo.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ModelRepository extends JpaRepository<Model, Long> {
}
