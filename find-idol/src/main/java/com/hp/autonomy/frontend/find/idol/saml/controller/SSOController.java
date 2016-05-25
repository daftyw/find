package com.hp.autonomy.frontend.find.idol.saml.controller;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Created by wind on 5/13/2016 AD.
 */
@Controller
@RequestMapping("/saml")
public class SSOController {

    /*
     * Checks if an HTTP request is forwarded from servlet.
     */
    private boolean isForwarded(HttpServletRequest request){
        if (request.getAttribute("javax.servlet.forward.request_uri") == null)
            return false;
        else
            return true;
    }

}
