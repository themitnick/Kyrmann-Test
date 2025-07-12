package com.assure.user_service.service;

import com.assure.user_service.client.PermissionClient;
import com.assure.user_service.client.RoleClient;
import com.assure.user_service.dto.PermissionDto;
import com.assure.user_service.dto.RoleDto;
import com.assure.user_service.dto.UtilisateurCompositeDto;
import com.assure.user_service.entity.Utilisateur;
import com.assure.user_service.repository.UtilisateurRepository;
import com.assure.user_service.fallback.ServiceFallbacks;
import com.assure.common.exception.ResourceNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UtilisateurCompositeService {
    private static final Logger logger = LoggerFactory.getLogger(UtilisateurCompositeService.class);
    private final UtilisateurRepository utilisateurRepository;
    private final RoleClient roleClient;
    private final PermissionClient permissionClient;

    public UtilisateurCompositeService(
            UtilisateurRepository utilisateurRepository,
            RoleClient roleClient,
            PermissionClient permissionClient) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleClient = roleClient;
        this.permissionClient = permissionClient;
    }

    public UtilisateurCompositeDto getUtilisateurComplet(UUID id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", id.toString()));

        UtilisateurCompositeDto dto = new UtilisateurCompositeDto();
        dto.setId(utilisateur.getId());
        dto.setIdentifiant(utilisateur.getIdentifiant());
        dto.setEmail(utilisateur.getEmail());
        dto.setTelephone(utilisateur.getTelephone());
        dto.setLanguePreferee(utilisateur.getLanguePreferee());
        dto.setActif(utilisateur.getActif());
        dto.setDateDerniereConnexion(utilisateur.getDateDerniereConnexion());

        dto.setRole(getRoleWithResilience(utilisateur.getRoleId()));
        dto.setPermissions(new HashSet<>(getPermissionsWithResilience()));

        return dto;
    }

    @CircuitBreaker(name = "roleService", fallbackMethod = "getRoleFallback")
    @Retry(name = "roleService")
    private RoleDto getRoleWithResilience(UUID roleId) {
        logger.info("Attempting to fetch role with ID: {}", roleId);
        return roleClient.getRole(roleId);
    }

    private RoleDto getRoleFallback(UUID roleId, Exception ex) {
        logger.warn("Fallback for role retrieval. Role ID: {}, Exception: {}", roleId, ex.getMessage());
        return ServiceFallbacks.getDefaultRole();
    }

    @CircuitBreaker(name = "permissionService", fallbackMethod = "getPermissionsFallback")
    @Retry(name = "permissionService")
    private List<PermissionDto> getPermissionsWithResilience() {
        logger.info("Attempting to fetch permissions");
        return permissionClient.getAllPermissions();
    }

    private List<PermissionDto> getPermissionsFallback(Exception ex) {
        logger.warn("Fallback for permissions retrieval. Exception: {}", ex.getMessage());
        return ServiceFallbacks.getDefaultPermissions();
    }
}
