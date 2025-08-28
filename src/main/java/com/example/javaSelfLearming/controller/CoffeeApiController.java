package com.example.javaSelfLearming.controller;

import com.example.javaSelfLearming.dto.CoffeeForm;
import com.example.javaSelfLearming.entity.Coffee;
import com.example.javaSelfLearming.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class CoffeeApiController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping("/coffees")
    public List<Coffee> getCoffees(){

        return coffeeRepository.findAll();
    }

    @GetMapping("/coffees/{id}")
    public ResponseEntity<Coffee> getCoffee(@PathVariable Long id){

        Coffee coffee = coffeeRepository.findById(id).orElseThrow(()-> null);

        return ResponseEntity.ok(coffee);
    }

    @PostMapping("/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm form){

        Coffee coffee = form.toEntity();

        log.info("엔티티화 성공 " + coffee.toString());

        coffeeRepository.save(coffee);

        return ResponseEntity.ok(coffee);
    }

    @PatchMapping("/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm form){
        Coffee coffee = form.toEntity();

        Coffee target = coffeeRepository.findById(id).orElseThrow(()-> null);

        if(target == null || coffee.getId() != id){
            log.info("id : {} , coffee : {}", id, coffee);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        log.info("id : {}, coffee : {}", id, coffee);

        target.patch(coffee);
        log.info(target.toString());
        Coffee updated = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee target = coffeeRepository.findById(id).orElseThrow(()-> null);
        log.info("id : {} , coffee : {}", id, target);

        if (target == null) {
            log.info("id : {}, coffee : {}", id, target);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


        coffeeRepository.delete(target);

        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
