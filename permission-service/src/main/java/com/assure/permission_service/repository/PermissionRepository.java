package com.assure.permission_service.repository;

import com.assure.permission_service.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    List<Permission> findByParentId(UUID parentId);
    List<Permission> findByCodeContainingIgnoreCase(String code);
    boolean existsByCode(String code);
}
