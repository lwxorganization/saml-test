package com.okta.developer.controller;


import com.okta.developer.controller.model.PreAuthUsername;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * "Pre-auth" landing page in which the user will enter their username. Depending on
 * the username suffix, the user will either be directed to provide a password for
 * database authentication, or the SAML auth flow will be initiated.
 *
 * The redirect to /doSaml will be picked up by the custom auth entry point defined
 * in {@link com.okta.developer.config.WebSecurityConfig}.
 *
 * @author jcavazos
 */
@Controller
public class IndexController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("username", new PreAuthUsername());
        return "index";
    }
}
