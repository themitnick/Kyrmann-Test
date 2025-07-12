package com.assure.user_service.service;

import com.assure.user_service.dto.UtilisateurDto;
import com.assure.user_service.entity.Utilisateur;
import com.assure.user_service.mapper.UtilisateurMapper;
import com.assure.user_service.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    @Transactional
    public UtilisateurDto createUtilisateur(UtilisateurDto utilisateurDto) {
        validateUniqueFields(utilisateurDto);
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDto);
        return utilisateurMapper.toDto(utilisateurRepository.save(utilisateur));
    }

    public UtilisateurDto getUtilisateur(UUID id) {
        return utilisateurRepository.findById(id)
                .map(utilisateurMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
    }

    public List<UtilisateurDto> getAllUtilisateurs() {
        return utilisateurMapper.toDtoList(utilisateurRepository.findAll());
    }

    public List<UtilisateurDto> getUtilisateursByRole(UUID roleId) {
        return utilisateurMapper.toDtoList(utilisateurRepository.findByRoleId(roleId));
    }

    @Transactional
    public UtilisateurDto updateUtilisateur(UUID id, UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        validateUniqueFieldsForUpdate(utilisateurDto, id);
        utilisateurMapper.updateEntityFromDto(utilisateurDto, utilisateur);
        return utilisateurMapper.toDto(utilisateurRepository.save(utilisateur));
    }

    @Transactional
    public void deleteUtilisateur(UUID id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        utilisateur.setDeleted(true);
        utilisateurRepository.save(utilisateur);
    }

    private void validateUniqueFields(UtilisateurDto dto) {
        if (utilisateurRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        if (utilisateurRepository.findByTelephone(dto.getTelephone()).isPresent()) {
            throw new RuntimeException("Telephone already exists");
        }
        if (utilisateurRepository.findByIdentifiant(dto.getIdentifiant()).isPresent()) {
            throw new RuntimeException("Identifiant already exists");
        }
    }

    private void validateUniqueFieldsForUpdate(UtilisateurDto dto, UUID id) {
        utilisateurRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> {
                    if (!u.getId().equals(id)) {
                        throw new RuntimeException("Email already exists");
                    }
                });
        utilisateurRepository.findByTelephone(dto.getTelephone())
                .ifPresent(u -> {
                    if (!u.getId().equals(id)) {
                        throw new RuntimeException("Telephone already exists");
                    }
                });
        utilisateurRepository.findByIdentifiant(dto.getIdentifiant())
                .ifPresent(u -> {
                    if (!u.getId().equals(id)) {
                        throw new RuntimeException("Identifiant already exists");
                    }
                });
    }
}
