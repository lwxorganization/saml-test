package com.okta.developer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Page to select which Identity Provider (IDP) to authenticate against when beginning SAML auth flow.
 * For this example, only Okta is defined.
 *
 * @author vdenotaris
 * @see com.okta.developer.config.SamlSecurityConfig.metadata()
 * @see <a href="https://github.com/vdenotaris/spring-boot-security-saml-sample/blob/master/src/main/java/com/vdenotaris/spring/boot/security/saml/web/controllers/SSOController.java">SSOController</a>
 */
@Controller
@RequestMapping("/saml")
public class SSOController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSOController.class);

    private final MetadataManager metadata;

    @Autowired
    public SSOController(MetadataManager metadata) {
        this.metadata = metadata;
    }

    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public String idpSelection(Model model, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("Current auth: {}", auth == null ? "NULL" : auth.getPrincipal());
        if (auth == null || (auth instanceof AnonymousAuthenticationToken)) {
            Set<String> idps = metadata.getIDPEntityNames();
            idps.forEach(idp -> LOGGER.info("Configured Identity Provider for SSO: {}", idp));
            model.addAttribute("idps", idps);
            return "redirect:/saml/login?diso=true&idp=" + metadata.getDefaultIDP();
        } else {
            LOGGER.warn("The current user is already logged in: {}", auth);
            return "redirect:/landing";
        }
    }
}
