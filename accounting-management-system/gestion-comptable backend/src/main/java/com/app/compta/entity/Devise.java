package com.app.compta.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "devise")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Devise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deviseReference;

    private String deviseLibelle;
    private String deviseLibelleISO;
    private String deviseSymbole;
}