package com.example.javaSelfLearming.dto;

import com.example.javaSelfLearming.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class CoffeeForm {
    private Long id;
    private String name;
    private int price;

    public Coffee toEntity(){
        return new Coffee(id, name, price);
    }
}
