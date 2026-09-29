package com.app.compta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "rsattestationrecuperee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RsAttestationRecuperee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rsattestationrecupereReference;

    private String rsattestationrecupereLibelle;

    @OneToMany(mappedBy = "rsAttestationRecuperee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FactureFrs> facturesFrs;
}