package com.app.compta.service;

import com.app.compta.entity.Devise;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.DeviseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviseService {

    private final DeviseRepository deviseRepository;

    public List<Devise> getAllDevises() {
        return deviseRepository.findAll();
    }

    public Devise getDeviseById(int id) {
        return deviseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Devise non trouvée avec l'id: " + id));
    }

    public Devise createDevise(Devise devise) {
        return deviseRepository.save(devise);
    }

    public Devise updateDevise(int id, Devise devise) {
        Devise existing = getDeviseById(id);
        existing.setDeviseLibelle(devise.getDeviseLibelle());
        existing.setDeviseLibelleISO(devise.getDeviseLibelleISO());
        existing.setDeviseSymbole(devise.getDeviseSymbole());
        return deviseRepository.save(existing);
    }

    public void deleteDevise(int id) {
        deviseRepository.deleteById(id);
    }
}