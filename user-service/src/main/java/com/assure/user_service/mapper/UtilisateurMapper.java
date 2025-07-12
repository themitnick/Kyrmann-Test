package com.assure.user_service.mapper;

import com.assure.user_service.dto.UtilisateurDto;
import com.assure.user_service.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    @Mapping(target = "permissionIds", ignore = true)
    UtilisateurDto toDto(Utilisateur utilisateur);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deletedBy", ignore = true)
    @Mapping(target = "activateAt", ignore = true)
    @Mapping(target = "activateBy", ignore = true)
    Utilisateur toEntity(UtilisateurDto dto);

    List<UtilisateurDto> toDtoList(List<Utilisateur> utilisateurs);

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
    void updateEntityFromDto(UtilisateurDto dto, @MappingTarget Utilisateur utilisateur);
}
