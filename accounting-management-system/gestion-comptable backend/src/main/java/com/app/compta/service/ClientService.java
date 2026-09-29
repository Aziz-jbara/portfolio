package com.app.compta.service;

import com.app.compta.entity.Client;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'id: " + id));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(int id, Client client) {
        Client existing = getClientById(id);
        existing.setClientsRaisonSocial(client.getClientsRaisonSocial());
        existing.setClientsAdresse(client.getClientsAdresse());
        existing.setClientsCodePostal(client.getClientsCodePostal());
        existing.setClientsTelephone(client.getClientsTelephone());
        existing.setClientsCodeTVA(client.getClientsCodeTVA());
        existing.setClientsFax(client.getClientsFax());
        existing.setClientsGouvernorat(client.getClientsGouvernorat());
        existing.setClientsPays(client.getClientsPays());
        return clientRepository.save(existing);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}