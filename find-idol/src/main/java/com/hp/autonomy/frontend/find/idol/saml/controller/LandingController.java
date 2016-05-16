package com.hp.autonomy.frontend.find.idol.saml.controller;


import com.hp.autonomy.frontend.configuration.authentication.CommunityPrincipal;
import com.hp.autonomy.frontend.find.idol.saml.steriotypes.CurrentUser;
import org.springframework.security.core.userdetails.User;
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
    public String landing(@CurrentUser UsernamePasswordAuthenticationToken user, Model model) {
		
		Object principal = user.getPrincipal();
		String username = "";
		if( principal instanceof CommunityPrincipal) {
			username = ((CommunityPrincipal) principal).getUsername();	
		} else if( principal instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
			
			username = ((CommunityPrincipal) token.getPrincipal()).getUsername();	
		}
        model.addAttribute("username", 	username);
        return "redirect:/public/";
    }

}
