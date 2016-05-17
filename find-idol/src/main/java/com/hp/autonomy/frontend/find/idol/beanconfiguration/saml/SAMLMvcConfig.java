package com.hp.autonomy.frontend.find.idol.beanconfiguration.saml;

import com.hp.autonomy.frontend.find.idol.saml.core.CurrentUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by wind on 5/13/2016 AD.
 */
@Configuration
public class SAMLMvcConfig extends WebMvcConfigurerAdapter{

    @Autowired
    CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(currentUserHandlerMethodArgumentResolver);
    }
}
