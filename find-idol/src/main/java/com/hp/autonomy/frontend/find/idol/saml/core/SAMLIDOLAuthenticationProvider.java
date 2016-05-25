package com.hp.autonomy.frontend.find.idol.saml.core;

import com.hp.autonomy.frontend.configuration.authentication.CommunityPrincipal;
import com.hp.autonomy.user.UserRoles;
import com.hp.autonomy.user.UserService;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.schema.XSString;
import org.opensaml.xml.schema.impl.XSAnyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.saml.SAMLAuthenticationProvider;
import org.springframework.security.saml.SAMLCredential;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wind on 5/17/2016 AD.
 */
public class SAMLIDOLAuthenticationProvider extends SAMLAuthenticationProvider {

    private UserService userService;

    public SAMLIDOLAuthenticationProvider() {}

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected Object getPrincipal(SAMLCredential credential, Object userDetail) {
        String username = credential.getNameID().getValue();
        System.out.println("Prinicipal: " + username);
        UserRoles userRoles = this.userService.getUser(username);
        return new CommunityPrincipal(userRoles.getUid(), username, userRoles.getSecurityInfo());
    }

}
