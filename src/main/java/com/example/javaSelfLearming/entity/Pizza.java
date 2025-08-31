package com.example.javaSelfLearming.entity;

import com.example.javaSelfLearming.dto.PizzaDto;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
public class Pizza {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    public static Pizza createPizza(PizzaDto dto){
        return new Pizza(dto.getId(), dto.getName(), dto.getPrice());
    }

    public void patch(PizzaDto dto){
        if(dto.getName() != null){
            this.name = dto.getName();
        }
        if(dto.getPrice() != 0){
            this.price = dto.getPrice();
        }
    }

}
