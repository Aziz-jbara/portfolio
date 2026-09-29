package com.app.compta.mapper;

import com.app.compta.entity.Fournisseur;
import com.app.compta.dto.FournisseurDTO;

public class FournisseurMapper {

    public static FournisseurDTO toDTO(Fournisseur f) {
        FournisseurDTO dto = new FournisseurDTO();

        dto.setFournisseurReference(f.getFournisseurReference());
        dto.setFournisseurRaisonSocial(f.getFournisseurRaisonSocial());
        dto.setFournisseurAdresse(f.getFournisseurAdresse());
        dto.setFournisseurCodePostal(f.getFournisseurCodePostal());
        dto.setFournisseurTelephone(f.getFournisseurTelephone());
        dto.setFournisseurCodeTVA(f.getFournisseurCodeTVA());
        dto.setFournisseursGouvernorat(f.getFournisseursGouvernorat());
        dto.setFournisseursPays(f.getFournisseursPays());
        dto.setFournisseursFax(f.getFournisseursFax());

        return dto;
    }
}