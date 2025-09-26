package com.vitorlfreitas.store.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Relation with Cart (UUID stored as BINARY(16))
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // Relation with Product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity = 1;
}

