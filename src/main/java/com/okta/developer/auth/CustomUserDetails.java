package com.okta.developer.auth;

import java.util.Collection;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails
//        extends User
    implements UserDetails
{


    private AuthMethod authMethod;

//    public CustomUserDetails(AuthMethod authMethod,
//                             String username,
//                             String password, Collection<? extends GrantedAuthority> authorities) {
////        super(username, password, authorities);
//        this.authMethod = authMethod;
//        this.username=username;
//        this.password=password;
//        this.authorities.add(authorities);
//    }

//    public CustomUserDetails(AuthMethod authMethod,
//                             String username,
//                             String password,
//                             boolean enabled,
//                             boolean accountNonExpired,
//                             boolean credentialsNonExpired,
//                             boolean accountNonLocked,
//                             Collection<? extends GrantedAuthority> authorities) {
////        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//        this.authMethod = authMethod;
//    }

    /***********************************************************************/

    private String username;
    private String password;
    private boolean enabled;
    private Set<SimpleGrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, boolean enable, Set<SimpleGrantedAuthority> authorities) {
        this.username= username;
        this.password=password;
        this.enabled=enable;
        this.authorities=authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
