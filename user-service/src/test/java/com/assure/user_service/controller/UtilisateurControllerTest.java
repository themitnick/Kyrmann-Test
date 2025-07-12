package com.assure.user_service.controller;

import com.assure.user_service.dto.UtilisateurDto;
import com.assure.user_service.service.UtilisateurService;
import com.assure.common.enums.LanguePreferee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UtilisateurController.class)
class UtilisateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UtilisateurService utilisateurService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUtilisateur_ShouldReturnCreatedUtilisateur() throws Exception {
        // Given
        UtilisateurDto utilisateurDto = createTestUtilisateurDto();
        UtilisateurDto savedUtilisateur = createTestUtilisateurDto();
        savedUtilisateur.setId(UUID.randomUUID());

        when(utilisateurService.createUtilisateur(any(UtilisateurDto.class)))
                .thenReturn(savedUtilisateur);

        // When & Then
        mockMvc.perform(post("/api/utilisateurs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(utilisateurDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identifiant").value("jean.dupont"))
                .andExpect(jsonPath("$.email").value("jean.dupont@example.com"))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void getUtilisateur_ShouldReturnUtilisateur() throws Exception {
        // Given
        UUID userId = UUID.randomUUID();
        UtilisateurDto utilisateurDto = createTestUtilisateurDto();
        utilisateurDto.setId(userId);

        when(utilisateurService.getUtilisateur(userId))
                .thenReturn(utilisateurDto);

        // When & Then
        mockMvc.perform(get("/api/utilisateurs/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.identifiant").value("jean.dupont"))
                .andExpect(jsonPath("$.email").value("jean.dupont@example.com"));
    }

    @Test
    void getAllUtilisateurs_ShouldReturnListOfUtilisateurs() throws Exception {
        // Given
        List<UtilisateurDto> utilisateurs = Arrays.asList(
                createTestUtilisateurDto(),
                createTestUtilisateurDto());

        when(utilisateurService.getAllUtilisateurs())
                .thenReturn(utilisateurs);

        // When & Then
        mockMvc.perform(get("/api/utilisateurs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].identifiant").value("jean.dupont"))
                .andExpect(jsonPath("$[1].identifiant").value("jean.dupont"));
    }

    @Test
    void getUtilisateursByRole_ShouldReturnUtilisateursWithRole() throws Exception {
        // Given
        UUID roleId = UUID.randomUUID();
        List<UtilisateurDto> utilisateurs = Arrays.asList(createTestUtilisateurDto());

        when(utilisateurService.getUtilisateursByRole(roleId))
                .thenReturn(utilisateurs);

        // When & Then
        mockMvc.perform(get("/api/utilisateurs/role/{roleId}", roleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].identifiant").value("jean.dupont"));
    }

    @Test
    void updateUtilisateur_ShouldReturnUpdatedUtilisateur() throws Exception {
        // Given
        UUID userId = UUID.randomUUID();
        UtilisateurDto utilisateurDto = createTestUtilisateurDto();
        utilisateurDto.setIdentifiant("jean.martin");

        UtilisateurDto updatedUtilisateur = createTestUtilisateurDto();
        updatedUtilisateur.setId(userId);
        updatedUtilisateur.setIdentifiant("jean.martin");

        when(utilisateurService.updateUtilisateur(eq(userId), any(UtilisateurDto.class)))
                .thenReturn(updatedUtilisateur);

        // When & Then
        mockMvc.perform(put("/api/utilisateurs/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(utilisateurDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.identifiant").value("jean.martin"));
    }

    @Test
    void deleteUtilisateur_ShouldReturnOk() throws Exception {
        // Given
        UUID userId = UUID.randomUUID();
        doNothing().when(utilisateurService).deleteUtilisateur(userId);

        // When & Then
        mockMvc.perform(delete("/api/utilisateurs/{id}", userId))
                .andExpect(status().isOk());
    }

    private UtilisateurDto createTestUtilisateurDto() {
        UtilisateurDto dto = new UtilisateurDto();
        dto.setIdentifiant("jean.dupont");
        dto.setEmail("jean.dupont@example.com");
        dto.setTelephone("+33123456789");
        dto.setRoleId(UUID.randomUUID());
        dto.setEstVerifie(true);
        dto.setActif(true);
        dto.setLanguePreferee(LanguePreferee.FRANCAIS);
        return dto;
    }
}
