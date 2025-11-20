package com.example.demo.repository;

import com.example.demo.model.SimpleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SimpleRepository extends JpaRepository<SimpleModel, UUID> {
}
