package com.assure.user_service.repository;

import com.assure.user_service.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID> {
    Optional<Utilisateur> findByEmail(String email);
    Optional<Utilisateur> findByTelephone(String telephone);
    Optional<Utilisateur> findByIdentifiant(String identifiant);
    List<Utilisateur> findByRoleId(UUID roleId);
}
