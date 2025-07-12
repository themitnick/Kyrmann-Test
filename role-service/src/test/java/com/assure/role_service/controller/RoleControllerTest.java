package com.assure.role_service.controller;

import com.assure.role_service.dto.RoleDto;
import com.assure.role_service.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoleController.class)
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RoleService roleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createRole_ShouldReturnCreatedRole() throws Exception {
        // Given
        RoleDto roleDto = createTestRoleDto();
        RoleDto savedRole = createTestRoleDto();
        savedRole.setId(UUID.randomUUID());

        when(roleService.createRole(any(RoleDto.class)))
                .thenReturn(savedRole);

        // When & Then
        mockMvc.perform(post("/api/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roleDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("ADMIN"))
                .andExpect(jsonPath("$.description").value("Administrateur système"))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void getRole_ShouldReturnRole() throws Exception {
        // Given
        UUID roleId = UUID.randomUUID();
        RoleDto roleDto = createTestRoleDto();
        roleDto.setId(roleId);

        when(roleService.getRole(roleId))
                .thenReturn(roleDto);

        // When & Then
        mockMvc.perform(get("/api/roles/{id}", roleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(roleId.toString()))
                .andExpect(jsonPath("$.nom").value("ADMIN"))
                .andExpect(jsonPath("$.description").value("Administrateur système"));
    }

    @Test
    void getAllRoles_ShouldReturnListOfRoles() throws Exception {
        // Given
        List<RoleDto> roles = Arrays.asList(
                createTestRoleDto(),
                createTestRoleDto());

        when(roleService.getAllRoles())
                .thenReturn(roles);

        // When & Then
        mockMvc.perform(get("/api/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nom").value("ADMIN"))
                .andExpect(jsonPath("$[1].nom").value("ADMIN"));
    }

    @Test
    void searchRoles_ShouldReturnFilteredRoles() throws Exception {
        // Given
        String searchTerm = "ADMIN";
        List<RoleDto> roles = Arrays.asList(createTestRoleDto());

        when(roleService.searchRoles(searchTerm))
                .thenReturn(roles);

        // When & Then
        mockMvc.perform(get("/api/roles/search")
                .param("nom", searchTerm))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nom").value("ADMIN"));
    }

    @Test
    void updateRole_ShouldReturnUpdatedRole() throws Exception {
        // Given
        UUID roleId = UUID.randomUUID();
        RoleDto roleDto = createTestRoleDto();
        roleDto.setNom("MANAGER");

        RoleDto updatedRole = createTestRoleDto();
        updatedRole.setId(roleId);
        updatedRole.setNom("MANAGER");

        when(roleService.updateRole(eq(roleId), any(RoleDto.class)))
                .thenReturn(updatedRole);

        // When & Then
        mockMvc.perform(put("/api/roles/{id}", roleId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roleDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(roleId.toString()))
                .andExpect(jsonPath("$.nom").value("MANAGER"));
    }

    @Test
    void deleteRole_ShouldReturnOk() throws Exception {
        // Given
        UUID roleId = UUID.randomUUID();
        doNothing().when(roleService).deleteRole(roleId);

        // When & Then
        mockMvc.perform(delete("/api/roles/{id}", roleId))
                .andExpect(status().isOk());
    }

    private RoleDto createTestRoleDto() {
        RoleDto dto = new RoleDto();
        dto.setNom("ADMIN");
        dto.setDescription("Administrateur système");
        dto.setActif(true);
        return dto;
    }
}
