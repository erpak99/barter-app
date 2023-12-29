package com.erpak.barter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barter")
public class Barter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User userOne;

    @ManyToOne
    @JoinColumn(name = "product1_id")
    private Product productOne;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User userTwo;

    @ManyToOne
    @JoinColumn(name = "product2_id")
    private Product productTwo;

}
