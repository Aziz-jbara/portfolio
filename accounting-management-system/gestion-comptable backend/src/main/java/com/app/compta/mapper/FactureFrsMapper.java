package com.app.compta.mapper;

import com.app.compta.entity.FactureFrs;
import com.app.compta.dto.FactureFrsDTO;

public class FactureFrsMapper {

    public static FactureFrsDTO toDTO(FactureFrs f) {
        FactureFrsDTO dto = new FactureFrsDTO();

        dto.setFacturesFRSReference(f.getFacturesFRSReference());
        dto.setFacturesFRSNumero(f.getFacturesFRSNumero());

        dto.setFacturesFRSMontantDevise(f.getFacturesFRSMontantDevise());
        dto.setFacturesFRSTauxChange(f.getFacturesFRSTauxChange());

        dto.setFacturesFRSHT(f.getFacturesFRSHT());
        dto.setFacturesFRSTVAPourcentage(f.getFacturesFRSTVAPourcentage());
        dto.setFacturesFRSTVA(f.getFacturesFRSTVA());
        dto.setFacturesFRSTTC(f.getFacturesFRSTTC());

        dto.setFacturesFRSTimbre(f.getFacturesFRSTimbre());
        dto.setFacturesFRSTotalFacture(f.getFacturesFRSTotalFacture());

        dto.setFacturesFRSRSPourcentage(f.getFacturesFRSRSPourcentage());
        dto.setFacturesFRSRSTotal(f.getFacturesFRSRSTotal());

        dto.setFacturesFRSMontantAPayer(f.getFacturesFRSMontantAPayer());
        dto.setFacturesFRSMontantPayee(f.getFacturesFRSMontantPayee());

        // RELATION IDS ONLY (safe approach)
        if (f.getFournisseur() != null) {
            dto.setFournisseurId(f.getFournisseur().getFournisseurReference());
        }

        if (f.getDevise() != null) {
            dto.setDeviseId(f.getDevise().getDeviseReference());
        }

        if (f.getRsAttestationRecuperee() != null) {
            dto.setRsAttestationId(f.getRsAttestationRecuperee().getRsattestationrecupereReference());
        }

        return dto;
    }

    public static FactureFrs toEntity(FactureFrsDTO dto) {
        FactureFrs f = new FactureFrs();

        f.setFacturesFRSReference(dto.getFacturesFRSReference());
        f.setFacturesFRSNumero(dto.getFacturesFRSNumero());

        f.setFacturesFRSMontantDevise(dto.getFacturesFRSMontantDevise());
        f.setFacturesFRSTauxChange(dto.getFacturesFRSTauxChange());

        f.setFacturesFRSHT(dto.getFacturesFRSHT());
        f.setFacturesFRSTVAPourcentage(dto.getFacturesFRSTVAPourcentage());
        f.setFacturesFRSTVA(dto.getFacturesFRSTVA());
        f.setFacturesFRSTTC(dto.getFacturesFRSTTC());

        f.setFacturesFRSTimbre(dto.getFacturesFRSTimbre());
        f.setFacturesFRSTotalFacture(dto.getFacturesFRSTotalFacture());

        f.setFacturesFRSRSPourcentage(dto.getFacturesFRSRSPourcentage());
        f.setFacturesFRSRSTotal(dto.getFacturesFRSRSTotal());

        f.setFacturesFRSMontantAPayer(dto.getFacturesFRSMontantAPayer());
        f.setFacturesFRSMontantPayee(dto.getFacturesFRSMontantPayee());

        return f;
    }
}