package com.assure.role_service.service;

import com.assure.role_service.dto.RoleDto;
import com.assure.role_service.entity.Role;
import com.assure.role_service.repository.RoleRepository;
import com.assure.common.exception.ResourceNotFoundException;
import com.assure.common.exception.DuplicateResourceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public RoleDto createRole(RoleDto dto) {
        if (roleRepository.existsByNom(dto.getNom())) {
            throw new DuplicateResourceException("Role", "nom", dto.getNom());
        }

        Role role = new Role();
        role.setNom(dto.getNom());
        role.setDescription(dto.getDescription());
        role.setActif(true);
        role.setCreatedAt(LocalDateTime.now());

        Role saved = roleRepository.save(role);
        return toDto(saved);
    }

    public RoleDto getRole(UUID id) {
        return roleRepository.findById(id)
            .map(this::toDto)
            .orElseThrow(() -> new ResourceNotFoundException("Role", id.toString()));
    }

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public List<RoleDto> searchRoles(String nom) {
        return roleRepository.findByNomContainingIgnoreCase(nom).stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public RoleDto updateRole(UUID id, RoleDto dto) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Role", id.toString()));

        role.setNom(dto.getNom());
        role.setDescription(dto.getDescription());
        role.setActif(dto.getActif());
        role.setUpdatedAt(LocalDateTime.now());

        Role updated = roleRepository.save(role);
        return toDto(updated);
    }

    @Transactional
    public void deleteRole(UUID id) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Role", id.toString()));

        role.setDeleted(true);
        role.setDeletedAt(LocalDateTime.now());
        roleRepository.save(role);
    }

    private RoleDto toDto(Role role) {
        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setNom(role.getNom());
        dto.setDescription(role.getDescription());
        dto.setActif(role.getActif());
        return dto;
    }
}
