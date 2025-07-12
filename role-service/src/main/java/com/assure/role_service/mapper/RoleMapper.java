package com.assure.role_service.mapper;

import com.assure.role_service.dto.RoleDto;
import com.assure.role_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto dto);
    List<RoleDto> toDtoList(List<Role> roles);
    void updateEntityFromDto(RoleDto dto, @MappingTarget Role role);
}
