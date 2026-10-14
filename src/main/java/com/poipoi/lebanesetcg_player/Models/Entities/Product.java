package com.poipoi.lebanesetcg_player.Models.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_type", nullable = false, length = 150)
    private String productType;

    @Column(name = "product_name", nullable = false, length = 150)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "quantity", length = 150)
    private Double quantity;

    @Column(name = "market_price", nullable = false, length = 150)
    private double marketPrice;

    @Column(name = "img_URl", nullable = false, length = 150)
    private String imgURL;
}