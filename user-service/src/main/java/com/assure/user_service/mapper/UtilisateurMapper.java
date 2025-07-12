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

    Utilisateur toEntity(UtilisateurDto dto);

    List<UtilisateurDto> toDtoList(List<Utilisateur> utilisateurs);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UtilisateurDto dto, @MappingTarget Utilisateur utilisateur);
}
