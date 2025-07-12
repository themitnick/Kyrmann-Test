package com.assure.permission_service.controller;

import com.assure.permission_service.dto.PermissionDto;
import com.assure.permission_service.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<PermissionDto> createPermission(@RequestBody PermissionDto permissionDto) {
        return ResponseEntity.ok(permissionService.createPermission(permissionDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDto> getPermission(@PathVariable UUID id) {
        return ResponseEntity.ok(permissionService.getPermission(id));
    }

    @GetMapping
    public ResponseEntity<List<PermissionDto>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<PermissionDto>> getChildPermissions(@PathVariable UUID parentId) {
        return ResponseEntity.ok(permissionService.getChildPermissions(parentId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDto> updatePermission(
            @PathVariable UUID id,
            @RequestBody PermissionDto permissionDto) {
        return ResponseEntity.ok(permissionService.updatePermission(id, permissionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable UUID id) {
        permissionService.deletePermission(id);
        return ResponseEntity.ok().build();
    }
}
