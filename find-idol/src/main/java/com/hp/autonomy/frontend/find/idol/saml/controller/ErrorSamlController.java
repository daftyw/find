package com.hp.autonomy.frontend.find.idol.saml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wind on 5/25/2016 AD.
 */
@Controller
public class ErrorSamlController  {

    /**
     * Error
     *
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("/error")
    public String displayError(ModelMap modelMap, HttpServletRequest request) {
        modelMap.put("isAuthError", true);
        modelMap.put("baseUrl", request.getRequestURI());
        return "error";
    }
}
