package com.vitorlfreitas.store.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@ToString(exclude = "user")
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "eircode")
    private String eircode;

    @Column(name = "county")
    private String county;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}