package com.example.javaSelfLearming.controller;


import com.example.javaSelfLearming.dto.CoffeeForm;
import com.example.javaSelfLearming.entity.Coffee;
import com.example.javaSelfLearming.service.CoffeeService;
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
    private CoffeeService coffeeService;

    @GetMapping("/coffees")
    public List<Coffee> getCoffees() {
        return coffeeService.index();
    }

    @GetMapping("/coffees/{id}")
    public Coffee getCoffee(@PathVariable Long id) {
        return coffeeService.show(id);
    }

    @PostMapping("/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm form) {
        Coffee created = coffeeService.create(form);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm form) {
        Coffee updated = coffeeService.update(id, form);
        return updated != null ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee deleted = coffeeService.delete(id);
        return deleted != null ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    @GetMapping("/coffees")
//    public List<Coffee> getCoffees(){
//
//        return coffeeRepository.findAll();
//    }
//
//    @GetMapping("/coffees/{id}")
//    public ResponseEntity<Coffee> getCoffee(@PathVariable Long id){
//
//        Coffee coffee = coffeeRepository.findById(id).orElseThrow(()-> null);
//
//        return ResponseEntity.ok(coffee);
//    }
//
//    @PostMapping("/coffees")
//    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm form){
//
//        Coffee coffee = form.toEntity();
//
//        log.info("엔티티화 성공 " + coffee.toString());
//
//        coffeeRepository.save(coffee);
//
//        return ResponseEntity.ok(coffee);
//    }
//
//    @PatchMapping("/coffees/{id}")
//    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm form){
//        Coffee coffee = form.toEntity();
//
//        Coffee target = coffeeRepository.findById(id).orElseThrow(()-> null);
//
//        if(target == null || coffee.getId() != id){
//            log.info("id : {} , coffee : {}", id, coffee);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        log.info("id : {}, coffee : {}", id, coffee);
//
//        target.patch(coffee);
//        log.info(target.toString());
//        Coffee updated = coffeeRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    @DeleteMapping("/coffees/{id}")
//    public ResponseEntity<Coffee> delete(@PathVariable Long id){
//        Coffee target = coffeeRepository.findById(id).orElseThrow(()-> null);
//        log.info("id : {} , coffee : {}", id, target);
//
//        if (target == null) {
//            log.info("id : {}, coffee : {}", id, target);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//
//        coffeeRepository.delete(target);
//
//        return ResponseEntity.status(HttpStatus.OK).build();
//
//    }
}
