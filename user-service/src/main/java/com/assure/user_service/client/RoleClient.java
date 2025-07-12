package com.assure.user_service.client;

import com.assure.user_service.dto.RoleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "role-service")
public interface RoleClient {
    @GetMapping("/api/roles/{id}")
    RoleDto getRole(@PathVariable("id") UUID id);
}
