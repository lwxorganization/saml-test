package com.okta.developer.newconfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysPermissionRepository extends JpaRepository<SysPermissionEntity,Integer>,
        JpaSpecificationExecutor<SysPermissionEntity> {
}
