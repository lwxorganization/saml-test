package com.okta.developer.newconfig;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * @author V.Sel
 * @date 2022/6/19
 * @desc
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role")
public class SysRoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 角色编码 */
    private String code;

    /** 角色名称 */
    private String name;

    @ManyToMany
    @JoinTable(name = "sys_role_permission", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<SysPermissionEntity> permissions;

}
