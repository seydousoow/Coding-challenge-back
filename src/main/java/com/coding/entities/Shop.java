package com.coding.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;
    @Column(unique = true, length = 50, nullable = false)
    private String name;

    /**
     * Variable to set the position of a shop. In this case, we will use 0 as the position of every user
     * Then the shops are going to be sorted by distance that is going to be the length between a user x (here 0) and the shop
     */
    @Column(nullable = false)
    private double position;

}
