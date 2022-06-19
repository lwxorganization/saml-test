//package com.okta.developer.newconfig;
//
//import java.util.Collection;
//import java.util.Set;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
///**
// * @author V.Sel
// * @date 2022/6/19
// * @desc
// */
//public class MyUserDetails implements UserDetails {
//
//    private String username;
//    private String password;
//    private boolean enabled;
//    private Set<SimpleGrantedAuthority> authorities;
//
//    public MyUserDetails(String username, String password, boolean enabled, Set<SimpleGrantedAuthority> authorities) {
//        this.username = username;
//        this.password = password;
//        this.enabled = enabled;
//        this.authorities = authorities;
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//}
