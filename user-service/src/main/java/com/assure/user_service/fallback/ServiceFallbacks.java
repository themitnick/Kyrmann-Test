package com.assure.user_service.fallback;

import com.assure.user_service.dto.RoleDto;
import com.assure.user_service.dto.PermissionDto;
import java.util.Collections;
import java.util.List;

public class ServiceFallbacks {
    public static RoleDto getDefaultRole() {
        RoleDto defaultRole = new RoleDto();
        defaultRole.setNom("DEFAULT_ROLE");
        defaultRole.setDescription("Rôle par défaut (service indisponible)");
        defaultRole.setActif(true);
        return defaultRole;
    }

    public static List<PermissionDto> getDefaultPermissions() {
        return Collections.emptyList();
    }
}
