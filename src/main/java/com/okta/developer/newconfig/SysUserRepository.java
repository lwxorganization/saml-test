package com.okta.developer.newconfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author V.Sel
 * @date 2022/6/19
 * @desc
 */
public interface SysUserRepository extends JpaRepository<SysUserEntity,Integer>, JpaSpecificationExecutor<SysUserEntity> {

    SysUserEntity findByUsername(String username);
}
