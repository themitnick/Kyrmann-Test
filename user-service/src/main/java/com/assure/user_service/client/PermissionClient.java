package com.assure.user_service.client;

import com.assure.user_service.dto.PermissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "permission-service")
public interface PermissionClient {
    @GetMapping("/api/permissions/{id}")
    PermissionDto getPermission(@PathVariable("id") UUID id);

    @GetMapping("/api/permissions")
    List<PermissionDto> getAllPermissions();
}
