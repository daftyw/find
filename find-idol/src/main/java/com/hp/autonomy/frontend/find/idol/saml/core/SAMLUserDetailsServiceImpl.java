package com.hp.autonomy.frontend.find.idol.saml.core;

import com.hp.autonomy.frontend.configuration.authentication.CommunityPrincipal;
import com.hp.autonomy.frontend.configuration.authentication.Role;
import com.hp.autonomy.frontend.configuration.authentication.Roles;
import com.hp.autonomy.frontend.find.idol.beanconfiguration.UserConfiguration;
import com.hp.autonomy.user.UserRoles;
import com.hp.autonomy.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * User Mapping Class
 *
 * Created by wind on 5/13/2016 AD.
 */
@Component
public class SAMLUserDetailsServiceImpl implements SAMLUserDetailsService {

    // Logger
    private static final Logger LOG = LoggerFactory.getLogger(SAMLUserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private GrantedAuthoritiesMapper grantedAuthoritiesMapper;

    public Object loadUserBySAML(SAMLCredential credential)
            throws UsernameNotFoundException {

        final Role user = new Role.Builder()
                .setName(UserConfiguration.IDOL_USER_ROLE)
                .setPrivileges(Collections.singleton("login"))
                .build();

        final Role admin = new Role.Builder()
                .setName(UserConfiguration.IDOL_ADMIN_ROLE)
                .setParent(Collections.singleton(user))
                .build();

        final Roles roles = new Roles(Arrays.asList(admin, user));

        // The method is supposed to identify local account of user referenced by
        // data in the SAML assertion and return UserDetails object describing the user.

        String username = credential.getNameID().getValue();
		System.out.println("User name: " + username);
        UserRoles userRoles = this.userService.getUser(username);

        if(!roles.areRolesAuthorized(new HashSet(userRoles.getRoles()), Collections.singleton("login"))) {
            throw new BadCredentialsException("Bad credentials");
        } else {

            String role = credential.getAttributeAsString("idp-role");
            String idpUsername = credential.getAttributeAsString("username");

            System.out.println("Local entity: " + credential.getLocalEntityID());
            System.out.println("Remote entity: " + credential.getRemoteEntityID());
            System.out.println("User name: " + username);
            System.out.println("Role: " + role);

            ArrayList grantedAuthorities = new ArrayList();
            Iterator mappedAuthorities = manipulateRole(userRoles.getRoles().iterator(), role);

            while(mappedAuthorities.hasNext()) {
                String simpleRole = (String)mappedAuthorities.next();
                grantedAuthorities.add(new SimpleGrantedAuthority(simpleRole));
            }

            Collection mappedAuthorities1 = this.grantedAuthoritiesMapper.mapAuthorities(grantedAuthorities);
            System.out.println("map: " + mappedAuthorities1);
            return new User(username,  "<password>", mappedAuthorities1);
        }

    }

    /**
     *
     * @param mappedAuthorities
     * @param role
     * @return
     */
    protected Iterator manipulateRole(Iterator mappedAuthorities, String role) {
        // Add role mapping code here
        return mappedAuthorities;
    }
}
