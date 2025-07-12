package com.assure.role_service.mapper;

import com.assure.role_service.dto.RoleDto;
import com.assure.role_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    @Mapping(target = "activateAt", ignore = true)
    @Mapping(target = "activateBy", ignore = true)
    Role toEntity(RoleDto dto);

    List<RoleDto> toDtoList(List<Role> roles);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    @Mapping(target = "activateAt", ignore = true)
    @Mapping(target = "activateBy", ignore = true)
    void updateEntityFromDto(RoleDto dto, @MappingTarget Role role);
}
