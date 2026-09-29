package com.app.compta.mapper;

import com.app.compta.entity.FactureClient;
import com.app.compta.dto.FactureClientDTO;

public class FactureClientMapper {

    public static FactureClientDTO toDTO(FactureClient f) {
        FactureClientDTO dto = new FactureClientDTO();

        dto.setFacturesClientReference(f.getFacturesClientReference());

        dto.setFacturesClientNumero(f.getFacturesClientNumero());

        dto.setFacturesClientMontantDevise(f.getFacturesClientMontantDevise());
        dto.setFacturesClientTauxChange(f.getFacturesClientTauxChange());

        dto.setFacturesClientHT(f.getFacturesClientHT());
        dto.setFacturesClientTVA(f.getFacturesClientTVA());
        dto.setFacturesClientTTC(f.getFacturesClientTTC());

        dto.setFacturesClientTimbre(f.getFacturesClientTimbre());
        dto.setFacturesClientTotalFacture(f.getFacturesClientTotalFacture());

        dto.setFacturesClientRSTVAPourcentage(f.getFacturesClientRSTVAPourcentage());
        dto.setFacturesClientRSTVA(f.getFacturesClientRSTVA());

        dto.setFacturesClientRSPourcentage(f.getFacturesClientRSPourcentage());
        dto.setFacturesClientRS(f.getFacturesClientRS());

        dto.setFacturesClientRSTotal(f.getFacturesClientRSTotal());

        dto.setFacturesClientMontantARecevoir(f.getFacturesClientMontantARecevoir());
        dto.setFacturesClientMontantVerse(f.getFacturesClientMontantVerse());
        dto.setFacturesClientMontantEcart(f.getFacturesClientMontantEcart());

        return dto;
    }

    public static FactureClient toEntity(FactureClientDTO dto) {
        FactureClient f = new FactureClient();

        f.setFacturesClientReference(dto.getFacturesClientReference());
        f.setFacturesClientReference(dto.getFacturesClientReference());

        f.setFacturesClientNumero(dto.getFacturesClientNumero());

        f.setFacturesClientMontantDevise(dto.getFacturesClientMontantDevise());
        f.setFacturesClientTauxChange(dto.getFacturesClientTauxChange());

        f.setFacturesClientHT(dto.getFacturesClientHT());
        f.setFacturesClientTVA(dto.getFacturesClientTVA());
        f.setFacturesClientTTC(dto.getFacturesClientTTC());

        f.setFacturesClientTimbre(dto.getFacturesClientTimbre());
        f.setFacturesClientTotalFacture(dto.getFacturesClientTotalFacture());

        f.setFacturesClientRSTVAPourcentage(dto.getFacturesClientRSTVAPourcentage());
        f.setFacturesClientRSTVA(dto.getFacturesClientRSTVA());

        f.setFacturesClientRSPourcentage(dto.getFacturesClientRSPourcentage());
        f.setFacturesClientRS(dto.getFacturesClientRS());

        f.setFacturesClientRSTotal(dto.getFacturesClientRSTotal());

        f.setFacturesClientMontantARecevoir(dto.getFacturesClientMontantARecevoir());
        f.setFacturesClientMontantVerse(dto.getFacturesClientMontantVerse());
        f.setFacturesClientMontantEcart(dto.getFacturesClientMontantEcart());

        return f;
    }
}