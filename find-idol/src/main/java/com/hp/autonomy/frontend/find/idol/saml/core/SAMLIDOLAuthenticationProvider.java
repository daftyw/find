package com.hp.autonomy.frontend.find.idol.saml.core;

import com.hp.autonomy.frontend.configuration.authentication.CommunityPrincipal;
import com.hp.autonomy.user.UserRoles;
import com.hp.autonomy.user.UserService;
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

/**
 * Created by wind on 5/17/2016 AD.
 */
public class SAMLIDOLAuthenticationProvider extends SAMLAuthenticationProvider {

    private UserService userService;

    private GrantedAuthoritiesMapper grantedAuthoritiesMapper;

    public SAMLIDOLAuthenticationProvider() {}

    public SAMLIDOLAuthenticationProvider(UserService userService, GrantedAuthoritiesMapper grantedAuthoritiesMapper) {
        this.userService = userService;
        this.grantedAuthoritiesMapper = grantedAuthoritiesMapper;
    }

    public void setGrantedAuthoritiesMapper(GrantedAuthoritiesMapper grantedAuthoritiesMapper) {
        this.grantedAuthoritiesMapper = grantedAuthoritiesMapper;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected Object getUserDetails(SAMLCredential credential) {
        String username = credential.getNameID().getValue();
        System.out.println("User name: " + username);
        UserRoles userRoles = this.userService.getUser(username);

        ArrayList grantedAuthorities = new ArrayList();
        Iterator mappedAuthorities = userRoles.getRoles().iterator();

        while(mappedAuthorities.hasNext()) {
            String role = (String)mappedAuthorities.next();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }

        Collection mappedAuthorities1 = this.grantedAuthoritiesMapper.mapAuthorities(grantedAuthorities);
		
		System.out.println("map: " + mappedAuthorities1);
        return new User(username,  "<password>", mappedAuthorities1);
    }

    @Override
    protected Object getPrincipal(SAMLCredential credential, Object userDetail) {
        String username = credential.getNameID().getValue();
        System.out.println("Prinicipal: " + username);
        UserRoles userRoles = this.userService.getUser(username);
        return new CommunityPrincipal(userRoles.getUid(), username, userRoles.getSecurityInfo());
    }
}
