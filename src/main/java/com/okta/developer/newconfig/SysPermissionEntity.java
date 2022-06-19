package com.okta.developer.newconfig;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
@Table(name = "sys_permission")
public class SysPermissionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 权限编码（标识） */
    private String code;

    /** 权限名称 */
    private String name;

    /** 权限URL */
    private String url;

}
