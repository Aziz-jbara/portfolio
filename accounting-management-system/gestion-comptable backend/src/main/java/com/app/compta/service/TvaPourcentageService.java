package com.app.compta.service;

import com.app.compta.entity.TvaPourcentage;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.TvaPourcentageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TvaPourcentageService {

    private final TvaPourcentageRepository tvaPourcentageRepository;

    public List<TvaPourcentage> getAllTvaPourcentages() {
        return tvaPourcentageRepository.findAll();
    }

    public TvaPourcentage getTvaPourcentageById(int id) {
        return tvaPourcentageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TVA non trouvée avec l'id: " + id));
    }

    public TvaPourcentage createTvaPourcentage(TvaPourcentage tva) {
        return tvaPourcentageRepository.save(tva);
    }

    public TvaPourcentage updateTvaPourcentage(int id, TvaPourcentage tva) {
        TvaPourcentage existing = getTvaPourcentageById(id);
        existing.setTvapourcentageValeur(tva.getTvapourcentageValeur());
        return tvaPourcentageRepository.save(existing);
    }

    public void deleteTvaPourcentage(int id) {
        tvaPourcentageRepository.deleteById(id);
    }
}