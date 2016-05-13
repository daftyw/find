package com.hp.autonomy.frontend.find.idol.saml.controller;

import com.hp.autonomy.frontend.find.idol.saml.steriotypes.CurrentUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wind on 5/13/2016 AD.
 */
@Controller
public class LandingController {


    @RequestMapping("/landing")
    public String landing(@CurrentUser User user, Model model) {
        model.addAttribute("username", 	user.getUsername());
        return "landing";
    }

}
