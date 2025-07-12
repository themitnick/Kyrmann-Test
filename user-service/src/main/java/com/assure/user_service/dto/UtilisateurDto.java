package com.assure.user_service.dto;

import com.assure.common.enums.LanguePreferee;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class UtilisateurDto {
    private UUID id;
    private String identifiant;
    private Boolean estVerifie;
    private String email;
    private String telephone;
    private LanguePreferee languePreferee;
    private LocalDateTime dateDerniereConnexion;
    private String origineConnexion;
    private String lastLoginIp;
    private String canalParDefaut;
    private UUID roleId;
    private Set<UUID> permissionIds;
    private Boolean actif;
}
