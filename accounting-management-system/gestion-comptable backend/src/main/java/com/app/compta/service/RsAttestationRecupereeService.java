package com.app.compta.service;

import com.app.compta.entity.RsAttestationRecuperee;
import com.app.compta.exception.ResourceNotFoundException;
import com.app.compta.repository.RsAttestationRecupereeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RsAttestationRecupereeService {

    private final RsAttestationRecupereeRepository rsAttestationRecupereeRepository;

    public List<RsAttestationRecuperee> getAllRsAttestations() {
        return rsAttestationRecupereeRepository.findAll();
    }

    public RsAttestationRecuperee getRsAttestationById(int id) {
        return rsAttestationRecupereeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RS Attestation non trouvée avec l'id: " + id));
    }

    public RsAttestationRecuperee createRsAttestation(RsAttestationRecuperee rs) {
        return rsAttestationRecupereeRepository.save(rs);
    }

    public RsAttestationRecuperee updateRsAttestation(int id, RsAttestationRecuperee rs) {
        RsAttestationRecuperee existing = getRsAttestationById(id);
        existing.setRsattestationrecupereLibelle(rs.getRsattestationrecupereLibelle());
        return rsAttestationRecupereeRepository.save(existing);
    }

    public void deleteRsAttestation(int id) {
        rsAttestationRecupereeRepository.deleteById(id);
    }
}