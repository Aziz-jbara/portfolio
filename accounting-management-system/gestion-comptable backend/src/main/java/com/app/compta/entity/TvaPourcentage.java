package com.app.compta.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tvapourcentage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TvaPourcentage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tvapourcentageReference;

    private float tvapourcentageValeur;
}