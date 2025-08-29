package com.example.javaSelfLearming.service;

import com.example.javaSelfLearming.dto.CoffeeForm;
import com.example.javaSelfLearming.entity.Coffee;
import com.example.javaSelfLearming.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).get();
    }

    public Coffee create(CoffeeForm form) {
        Coffee coffee = form.toEntity();

        if (coffee.getId() != null) {
            return null;
        }

        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id, CoffeeForm form) {
        Coffee coffee = form.toEntity();

        Coffee target = coffeeRepository.findById(id).get();

        if (target == null || target.getId() != id) {
            return null;
        }

        target.patch(coffee);
        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id){
        Coffee target = coffeeRepository.findById(id).get();

        if (target == null) {
            return null;
        }

        coffeeRepository.delete(target);

        return target;
    }

}
