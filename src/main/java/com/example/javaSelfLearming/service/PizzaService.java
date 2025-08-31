package com.example.javaSelfLearming.service;

import com.example.javaSelfLearming.dto.PizzaDto;
import com.example.javaSelfLearming.entity.Pizza;
import com.example.javaSelfLearming.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<PizzaDto> getPizzas(){
        return pizzaRepository.findAll().stream().map(pizza -> PizzaDto.createPizzaDto(pizza)
        ).collect(Collectors.toList());
    }

    public PizzaDto getPizza(Long id){
        Pizza created = pizzaRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("피자 조회 실패! 조회한 id값이 존재하지 않습니다."));


        return PizzaDto.createPizzaDto(created);
    }

    public PizzaDto create(PizzaDto dto){
        Pizza pizza = Pizza.createPizza(dto);

        Pizza created = pizzaRepository.save(pizza);

        return PizzaDto.createPizzaDto(created);
    }

    public PizzaDto update(Long id, PizzaDto dto){
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(()->
        new IllegalArgumentException("데이터 수정 실패! id가 존재하지 않습니다."));

        pizza.patch(dto);

        Pizza updated = pizzaRepository.save(pizza);

        return PizzaDto.createPizzaDto(updated);
    }

    public PizzaDto delete(Long id){
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("데이터 삭제 실패! id가 존재하지 않습니다.")
        );


        pizzaRepository.delete(pizza);

        return PizzaDto.createPizzaDto(pizza);
    }
}
