package com.assure.user_service.controller;

import com.assure.user_service.dto.UtilisateurCompositeDto;
import com.assure.user_service.service.UtilisateurCompositeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/utilisateurs/composite")
public class UtilisateurCompositeController {
    private final UtilisateurCompositeService utilisateurCompositeService;

    public UtilisateurCompositeController(UtilisateurCompositeService utilisateurCompositeService) {
        this.utilisateurCompositeService = utilisateurCompositeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurCompositeDto> getUtilisateurComplet(@PathVariable UUID id) {
        return ResponseEntity.ok(utilisateurCompositeService.getUtilisateurComplet(id));
    }
}
