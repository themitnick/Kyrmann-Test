package com.assure.user_service.service;

import com.assure.user_service.dto.UtilisateurDto;
import com.assure.user_service.entity.Utilisateur;
import com.assure.user_service.mapper.UtilisateurMapper;
import com.assure.user_service.repository.UtilisateurRepository;
import com.assure.common.enums.LanguePreferee;
import com.assure.common.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private UtilisateurMapper utilisateurMapper;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @Test
    void createUtilisateur_ShouldReturnCreatedUtilisateur() {
        // Given
        UtilisateurDto utilisateurDto = createTestUtilisateurDto();
        Utilisateur utilisateur = createTestUtilisateur();
        Utilisateur savedUtilisateur = createTestUtilisateur();
        savedUtilisateur.setId(UUID.randomUUID());

        when(utilisateurMapper.toEntity(utilisateurDto)).thenReturn(utilisateur);
        when(utilisateurRepository.save(utilisateur)).thenReturn(savedUtilisateur);
        when(utilisateurMapper.toDto(savedUtilisateur)).thenReturn(utilisateurDto);

        // When
        UtilisateurDto result = utilisateurService.createUtilisateur(utilisateurDto);

        // Then
        assertThat(result).isNotNull();
        verify(utilisateurRepository).save(utilisateur);
    }

    @Test
    void getUtilisateur_WithValidId_ShouldReturnUtilisateur() {
        // Given
        UUID userId = UUID.randomUUID();
        Utilisateur utilisateur = createTestUtilisateur();
        utilisateur.setId(userId);
        UtilisateurDto utilisateurDto = createTestUtilisateurDto();

        when(utilisateurRepository.findById(userId)).thenReturn(Optional.of(utilisateur));
        when(utilisateurMapper.toDto(utilisateur)).thenReturn(utilisateurDto);

        // When
        UtilisateurDto result = utilisateurService.getUtilisateur(userId);

        // Then
        assertThat(result).isNotNull();
        verify(utilisateurRepository).findById(userId);
    }

    @Test
    void getUtilisateur_WithInvalidId_ShouldThrowException() {
        // Given
        UUID userId = UUID.randomUUID();
        when(utilisateurRepository.findById(userId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> utilisateurService.getUtilisateur(userId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Utilisateur not found with id: " + userId);
    }

    @Test
    void getAllUtilisateurs_ShouldReturnAllUtilisateurs() {
        // Given
        List<Utilisateur> utilisateurs = Arrays.asList(
                createTestUtilisateur(),
                createTestUtilisateur());
        List<UtilisateurDto> utilisateurDtos = Arrays.asList(
                createTestUtilisateurDto(),
                createTestUtilisateurDto());

        when(utilisateurRepository.findAll()).thenReturn(utilisateurs);
        when(utilisateurMapper.toDto(any(Utilisateur.class)))
                .thenReturn(createTestUtilisateurDto());

        // When
        List<UtilisateurDto> result = utilisateurService.getAllUtilisateurs();

        // Then
        assertThat(result).hasSize(2);
        verify(utilisateurRepository).findAll();
    }

    @Test
    void getUtilisateursByRole_ShouldReturnUtilisateursWithRole() {
        // Given
        UUID roleId = UUID.randomUUID();
        List<Utilisateur> utilisateurs = Arrays.asList(createTestUtilisateur());

        when(utilisateurRepository.findByRoleId(roleId)).thenReturn(utilisateurs);
        when(utilisateurMapper.toDto(any(Utilisateur.class)))
                .thenReturn(createTestUtilisateurDto());

        // When
        List<UtilisateurDto> result = utilisateurService.getUtilisateursByRole(roleId);

        // Then
        assertThat(result).hasSize(1);
        verify(utilisateurRepository).findByRoleId(roleId);
    }

    @Test
    void updateUtilisateur_WithValidId_ShouldReturnUpdatedUtilisateur() {
        // Given
        UUID userId = UUID.randomUUID();
        UtilisateurDto utilisateurDto = createTestUtilisateurDto();
        Utilisateur existingUtilisateur = createTestUtilisateur();
        existingUtilisateur.setId(userId);
        Utilisateur updatedUtilisateur = createTestUtilisateur();
        updatedUtilisateur.setId(userId);

        when(utilisateurRepository.findById(userId)).thenReturn(Optional.of(existingUtilisateur));
        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(updatedUtilisateur);
        when(utilisateurMapper.toDto(updatedUtilisateur)).thenReturn(utilisateurDto);

        // When
        UtilisateurDto result = utilisateurService.updateUtilisateur(userId, utilisateurDto);

        // Then
        assertThat(result).isNotNull();
        verify(utilisateurRepository).findById(userId);
        verify(utilisateurRepository).save(any(Utilisateur.class));
    }

    @Test
    void updateUtilisateur_WithInvalidId_ShouldThrowException() {
        // Given
        UUID userId = UUID.randomUUID();
        UtilisateurDto utilisateurDto = createTestUtilisateurDto();

        when(utilisateurRepository.findById(userId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> utilisateurService.updateUtilisateur(userId, utilisateurDto))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void deleteUtilisateur_WithValidId_ShouldDeleteUtilisateur() {
        // Given
        UUID userId = UUID.randomUUID();
        Utilisateur utilisateur = createTestUtilisateur();

        when(utilisateurRepository.findById(userId)).thenReturn(Optional.of(utilisateur));

        // When
        utilisateurService.deleteUtilisateur(userId);

        // Then
        verify(utilisateurRepository).findById(userId);
        verify(utilisateurRepository).delete(utilisateur);
    }

    @Test
    void deleteUtilisateur_WithInvalidId_ShouldThrowException() {
        // Given
        UUID userId = UUID.randomUUID();

        when(utilisateurRepository.findById(userId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> utilisateurService.deleteUtilisateur(userId))
                .isInstanceOf(ResourceNotFoundException.class);
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

    private Utilisateur createTestUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant("jean.dupont");
        utilisateur.setEmail("jean.dupont@example.com");
        utilisateur.setTelephone("+33123456789");
        utilisateur.setRoleId(UUID.randomUUID());
        utilisateur.setEstVerifie(true);
        utilisateur.setActif(true);
        utilisateur.setLanguePreferee(LanguePreferee.FRANCAIS);
        return utilisateur;
    }
}
