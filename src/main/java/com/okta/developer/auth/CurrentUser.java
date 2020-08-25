package com.okta.developer.auth;

import java.lang.annotation.*;

/**
 * Represents the current authenticated user during HTTP requests
 * @author vdenotaris
 * @see <a href="https://github.com/vdenotaris/spring-boot-security-saml-sample/blob/master/src/main/java/com/vdenotaris/spring/boot/security/saml/web/stereotypes/CurrentUser.java">CurrentUser</a>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}
