package com.app.compta.mapper;

import com.app.compta.entity.Client;
import com.app.compta.dto.ClientDTO;

public class ClientMapper {

    public static ClientDTO toDTO(Client c) {
        ClientDTO dto = new ClientDTO();

        dto.setClientsReference(c.getClientsReference());
        dto.setClientsRaisonSocial(c.getClientsRaisonSocial());
        dto.setClientsAdresse(c.getClientsAdresse());
        dto.setClientsCodePostal(c.getClientsCodePostal());
        dto.setClientsTelephone(c.getClientsTelephone());
        dto.setClientsCodeTVA(c.getClientsCodeTVA());
        dto.setClientsFax(c.getClientsFax());
        dto.setClientsGouvernorat(c.getClientsGouvernorat());
        dto.setClientsPays(c.getClientsPays());

        return dto;
    }

    public static Client toEntity(ClientDTO dto) {
        Client c = new Client();

        c.setClientsReference(dto.getClientsReference());
        c.setClientsRaisonSocial(dto.getClientsRaisonSocial());
        c.setClientsAdresse(dto.getClientsAdresse());
        c.setClientsCodePostal(dto.getClientsCodePostal());
        c.setClientsTelephone(dto.getClientsTelephone());
        c.setClientsCodeTVA(dto.getClientsCodeTVA());
        c.setClientsFax(dto.getClientsFax());
        c.setClientsGouvernorat(dto.getClientsGouvernorat());
        c.setClientsPays(dto.getClientsPays());

        return c;
    }
}