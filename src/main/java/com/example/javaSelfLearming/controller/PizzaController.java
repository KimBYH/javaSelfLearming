package com.example.javaSelfLearming.controller;

import com.example.javaSelfLearming.dto.PizzaDto;
import com.example.javaSelfLearming.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("")
    public ResponseEntity<List<PizzaDto>> getPizzas(){
        List<PizzaDto> dtos = pizzaService.getPizzas();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDto> getPizza(@PathVariable Long id){
        PizzaDto dto = pizzaService.getPizza(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping("")
    public ResponseEntity<PizzaDto> create(@RequestBody PizzaDto dto){
        PizzaDto pizza = pizzaService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(pizza);
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<PizzaDto> update(@PathVariable Long id,
                                           @RequestBody PizzaDto dto){
        PizzaDto pizza = pizzaService.update(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<PizzaDto> delete(@PathVariable Long id){
        PizzaDto deleted = pizzaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
