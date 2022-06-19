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
@Table(name = "sys_user")
public class SysUserEntity  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    @ManyToMany
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<SysRoleEntity> roles;
}
