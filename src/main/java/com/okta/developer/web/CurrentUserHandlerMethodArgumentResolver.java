package com.okta.developer.web;

import com.okta.developer.auth.CombinedUserDetailsService;
import com.okta.developer.auth.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Resolves the {@link CurrentUser} argument supplied to controller methods
 * @see <a href="https://github.com/vdenotaris/spring-boot-security-saml-sample/blob/master/src/main/java/com/vdenotaris/spring/boot/security/saml/web/core/CurrentUserHandlerMethodArgumentResolver.java">CurrentUserHandlerMethodArgumentResolver</a>
 * @author vdenotaris
 * @author jcavazos
 */
@Component
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final CombinedUserDetailsService combinedUserDetailsService;

    @Autowired
    public CurrentUserHandlerMethodArgumentResolver(CombinedUserDetailsService combinedUserDetailsService) {
        this.combinedUserDetailsService = combinedUserDetailsService;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(CurrentUser.class) != null
                && methodParameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        if (this.supportsParameter(methodParameter)) {
            Authentication authentication = (Authentication) nativeWebRequest.getUserPrincipal();

            if (authentication.getPrincipal() instanceof User) {
                return authentication.getPrincipal();
            } else if (authentication.getPrincipal() instanceof String) {
                return combinedUserDetailsService.loadUserByUsername((String)authentication.getPrincipal());
            }

            throw new RuntimeException("Unsupported principal: "+authentication.getPrincipal());
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }
}
