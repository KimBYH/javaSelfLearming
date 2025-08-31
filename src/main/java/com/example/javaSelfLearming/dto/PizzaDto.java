package com.example.javaSelfLearming.dto;

import com.example.javaSelfLearming.entity.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PizzaDto {

    private Long id;
    private String name;
    private int price;

    public static PizzaDto createPizzaDto(Pizza pizza){
        return new PizzaDto(pizza.getId(), pizza.getName(), pizza.getPrice());
    }
}
