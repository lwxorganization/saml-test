package com.okta.developer.auth;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.okta.developer.newconfig.SysPermissionEntity;
import com.okta.developer.newconfig.SysUserEntity;
import com.okta.developer.newconfig.SysUserRepository;
import com.okta.developer.repository.UserRepository;
import org.apache.commons.codec.binary.Hex;
import org.opensaml.saml2.core.NameID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Supplies {@link UserDetails} information for both DB and SAML users
 * @author jcavazos
 */
@Service
public class CombinedUserDetailsService implements UserDetailsService, SAMLUserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CombinedUserDetailsService.class);

    private final UserRepository userRepository;

    @Autowired
    public CombinedUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        StoredUser storedUser = lookupUser(s);
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_admin");
//        return new CustomUserDetails(
//                AuthMethod.DATABASE,
//                storedUser.getUsername(),
//                storedUser.getPasswordHash(),
//                Arrays.asList(authority));
        CustomUserDetails userDetails=  newLoadUserByUsername(s);
        userDetails.setAuthMethod(AuthMethod.DATABASE);
        return userDetails;
    }

    @Override
    public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {
        NameID nameID= credential.getNameID();
        LOGGER.info("Loading UserDetails by SAMLCredentials: {}", nameID);
        LOGGER.info(" getAdditionalData: {} " ,credential.getAdditionalData());
        LOGGER.info(" getAuthenticationAssertion: {} " ,credential.getAuthenticationAssertion());
        LOGGER.info(" getRelayState: {} " ,credential.getRelayState());
        LOGGER.info(" getRemoteEntityID: {} " ,credential.getRemoteEntityID());
        LOGGER.info(" getAttributes: {} " ,credential.getAttributes());
        LOGGER.info(" getSPNameQualifier: {} " ,nameID.getSPNameQualifier());
        LOGGER.info(" getNameQualifier: {} " ,nameID.getNameQualifier());
        LOGGER.info(" getElementQName: {} " ,nameID.getElementQName());
        LOGGER.info(" getFormat: {} " ,nameID.getFormat());
        LOGGER.info(" getSchemaLocation: {} " ,nameID.getSchemaLocation());
        LOGGER.info(" getNoNamespaceSchemaLocation: {} " ,nameID.getNoNamespaceSchemaLocation());
        LOGGER.info(" getSchemaType: {} " ,nameID.getSchemaType());
        LOGGER.info(" getSPProvidedID: {} " ,nameID.getSPProvidedID());
        LOGGER.info(" getValidators: {} " ,nameID.getValidators());
        StoredUser storedUser = lookupUser(nameID.getValue());
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_admin");
//
//        return new CustomUserDetails(
//                AuthMethod.SAML,
//                storedUser.getUsername(),
//                storedUser.getPasswordHash(),
//                Arrays.asList(authority));
        CustomUserDetails userDetails=  newLoadUserByUsername(nameID.getValue());
        userDetails.setAuthMethod(AuthMethod.SAML);
        return userDetails;
    }

    @Resource
    private SysUserRepository sysUserRepository;

    private CustomUserDetails newLoadUserByUsername(String username){
        SysUserEntity sysUserEntity = sysUserRepository.findByUsername(username);
        if (null == sysUserEntity) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Set<SimpleGrantedAuthority> authorities = sysUserEntity.getRoles().stream()
                .flatMap(roleId->roleId.getPermissions().stream())
                .map(SysPermissionEntity::getCode)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new CustomUserDetails(sysUserEntity.getUsername(), sysUserEntity.getPassword(), true, authorities);
    }

    private StoredUser lookupUser(String username) {
        LOGGER.info("Loading UserDetails by username: {}", username);

        Optional<StoredUser> user = userRepository.findByUsernameIgnoreCase(username);

        if (!user.isPresent()) {
            StoredUser storedUser = new StoredUser();
            storedUser.setUsername(username);
            storedUser.setId(UUID.randomUUID().toString());
            storedUser.setPasswordHash(Hex.encodeHexString("liuweixiao".getBytes(StandardCharsets.UTF_8)));
            LOGGER.error("User not found in database: {}", user);
            return storedUser;
//            throw new UsernameNotFoundException(username);
        }

        return user.get();
    }
}
