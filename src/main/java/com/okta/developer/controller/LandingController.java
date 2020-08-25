package com.okta.developer.controller;

import com.okta.developer.auth.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This page should be guarded by the app's combined authentication. Thus, only users who
 * have successfully authenticated via DB or SAML auth should have access
 *
 * @see <a href="https://github.com/vdenotaris/spring-boot-security-saml-sample/blob/master/src/main/java/com/vdenotaris/spring/boot/security/saml/web/controllers/LandingController.java">LandingController</a>
 *
 * @author vdenotaris
 */
@Controller
public class LandingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LandingController.class);

    @RequestMapping("/landing")
    public String landing(@CurrentUser User user, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("Current auth: {}", authentication==null?"NULL":authentication.getPrincipal());
        model.addAttribute("username", user.getUsername());
        return "landing";
    }
}
