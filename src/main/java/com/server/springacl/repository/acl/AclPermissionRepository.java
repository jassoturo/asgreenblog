package com.server.springacl.repository.acl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AclPermissionRepository extends JpaRepository<AclPermissionEntity, Integer> {
}
