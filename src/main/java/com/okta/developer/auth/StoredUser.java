package com.okta.developer.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author jcavazos
 */
@Entity
@Table(name = "USER")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StoredUser implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passwordHash;
}
