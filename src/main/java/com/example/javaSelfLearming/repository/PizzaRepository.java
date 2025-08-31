package com.example.javaSelfLearming.repository;

import com.example.javaSelfLearming.entity.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Long> {
    @Override
    ArrayList<Pizza> findAll();
}
