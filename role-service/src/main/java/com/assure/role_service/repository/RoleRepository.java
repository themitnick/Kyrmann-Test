package com.assure.role_service.repository;

import com.assure.role_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    List<Role> findByNomContainingIgnoreCase(String nom);
    boolean existsByNom(String nom);
}
