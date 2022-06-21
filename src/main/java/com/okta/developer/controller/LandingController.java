package com.okta.developer.controller;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String landing(Model model) {
        setAttribute(model);
        return "landing";
    }

    private void setAttribute(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = StringUtils.EMPTY;
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                username = ((User) principal).getUsername();
            } else {
                username = principal.toString();
            }
        }
        LOGGER.info("Current auth: {}", authentication == null ? "NULL" : authentication.getPrincipal());
        model.addAttribute("username", username);
    }

    /**
     * @desc when using logout page to login ,it will be redirected to this ,
     * @return
     */
    @GetMapping("/login")
    public String login(Model model) {
        setAttribute(model);
        return "landing";
    }


    @GetMapping("/hello")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    public String test() {
        return "hello world !";
    }
}
