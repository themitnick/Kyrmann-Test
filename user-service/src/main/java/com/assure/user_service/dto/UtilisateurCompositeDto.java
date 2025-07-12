package com.assure.user_service.dto;

import com.assure.common.enums.LanguePreferee;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class UtilisateurCompositeDto {
    private UUID id;
    private String identifiant;
    private String email;
    private String telephone;
    private LanguePreferee languePreferee;
    private RoleDto role;
    private Set<PermissionDto> permissions;
    private Boolean actif;
    private LocalDateTime dateDerniereConnexion;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getIdentifiant() { return identifiant; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public LanguePreferee getLanguePreferee() { return languePreferee; }
    public void setLanguePreferee(LanguePreferee languePreferee) { this.languePreferee = languePreferee; }
    public RoleDto getRole() { return role; }
    public void setRole(RoleDto role) { this.role = role; }
    public Set<PermissionDto> getPermissions() { return permissions; }
    public void setPermissions(Set<PermissionDto> permissions) { this.permissions = permissions; }
    public Boolean getActif() { return actif; }
    public void setActif(Boolean actif) { this.actif = actif; }
    public LocalDateTime getDateDerniereConnexion() { return dateDerniereConnexion; }
    public void setDateDerniereConnexion(LocalDateTime dateDerniereConnexion) { this.dateDerniereConnexion = dateDerniereConnexion; }
}
