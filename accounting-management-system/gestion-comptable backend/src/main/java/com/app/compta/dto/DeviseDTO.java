package com.app.compta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviseDTO {

    private int deviseReference;
    private String deviseLibelle;
    private String deviseLibelleISO;
    private String deviseSymbole;
}