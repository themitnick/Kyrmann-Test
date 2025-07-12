package com.assure.permission_service.service;

import com.assure.permission_service.dto.PermissionDto;
import com.assure.permission_service.entity.Permission;
import com.assure.permission_service.repository.PermissionRepository;
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
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    public PermissionDto createPermission(PermissionDto dto) {
        if (permissionRepository.existsByCode(dto.getCode())) {
            throw new DuplicateResourceException("Permission", "code", dto.getCode());
        }

        Permission permission = new Permission();
        permission.setCode(dto.getCode());
        permission.setDescription(dto.getDescription());
        permission.setActif(true);
        permission.setCreatedAt(LocalDateTime.now());

        if (dto.getParentId() != null) {
            Permission parent = permissionRepository.findById(dto.getParentId())
                .orElseThrow(() -> new ResourceNotFoundException("Permission", dto.getParentId().toString()));
            permission.setParent(parent);
        }

        Permission saved = permissionRepository.save(permission);
        return toDto(saved);
    }

    public PermissionDto getPermission(UUID id) {
        return permissionRepository.findById(id)
            .map(this::toDto)
            .orElseThrow(() -> new ResourceNotFoundException("Permission", id.toString()));
    }

    public List<PermissionDto> getAllPermissions() {
        return permissionRepository.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public List<PermissionDto> getChildPermissions(UUID parentId) {
        return permissionRepository.findByParentId(parentId).stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public PermissionDto updatePermission(UUID id, PermissionDto dto) {
        Permission permission = permissionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Permission", id.toString()));

        permission.setCode(dto.getCode());
        permission.setDescription(dto.getDescription());
        permission.setActif(dto.getActif());
        permission.setUpdatedAt(LocalDateTime.now());

        if (dto.getParentId() != null && !dto.getParentId().equals(id)) {
            Permission parent = permissionRepository.findById(dto.getParentId())
                .orElseThrow(() -> new ResourceNotFoundException("Permission", dto.getParentId().toString()));
            permission.setParent(parent);
        }

        Permission updated = permissionRepository.save(permission);
        return toDto(updated);
    }

    @Transactional
    public void deletePermission(UUID id) {
        Permission permission = permissionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Permission", id.toString()));

        permission.setDeleted(true);
        permission.setDeletedAt(LocalDateTime.now());
        permissionRepository.save(permission);
    }

    private PermissionDto toDto(Permission permission) {
        PermissionDto dto = new PermissionDto();
        dto.setId(permission.getId());
        dto.setCode(permission.getCode());
        dto.setDescription(permission.getDescription());
        dto.setActif(permission.getActif());
        if (permission.getParent() != null) {
            dto.setParentId(permission.getParent().getId());
        }
        return dto;
    }
}
