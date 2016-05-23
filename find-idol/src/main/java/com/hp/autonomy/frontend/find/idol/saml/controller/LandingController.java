package com.hp.autonomy.frontend.find.idol.saml.controller;


import com.hp.autonomy.frontend.configuration.authentication.CommunityPrincipal;
import com.hp.autonomy.frontend.find.idol.saml.steriotypes.CurrentUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.providers.ExpiringUsernameAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by wind on 5/13/2016 AD.
 */
@Controller
public class LandingController {

    @RequestMapping("/landing")
    public String landing(@CurrentUser Object user, Model model) {
        String username;
        if(user instanceof CommunityPrincipal) {
            CommunityPrincipal principal = (CommunityPrincipal) user;
            username = principal.getUsername();
        } else if(user instanceof ExpiringUsernameAuthenticationToken) {
            CommunityPrincipal principal = (CommunityPrincipal) ((ExpiringUsernameAuthenticationToken) user).getPrincipal();
            username = principal.getUsername();
        } else {
            username = "N/A";
        }

        model.addAttribute("username", username);
        return "redirect:/public/#find";
    }

}
