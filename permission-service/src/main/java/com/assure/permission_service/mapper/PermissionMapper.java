package com.assure.permission_service.mapper;

import com.assure.permission_service.dto.PermissionDto;
import com.assure.permission_service.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    @Mapping(target = "parentId", source = "parent.id")
    PermissionDto toDto(Permission permission);

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    @Mapping(target = "activateAt", ignore = true)
    @Mapping(target = "activateBy", ignore = true)
    Permission toEntity(PermissionDto dto);

    List<PermissionDto> toDtoList(List<Permission> permissions);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    @Mapping(target = "activateAt", ignore = true)
    @Mapping(target = "activateBy", ignore = true)
    void updateEntityFromDto(PermissionDto dto, @MappingTarget Permission permission);
}
