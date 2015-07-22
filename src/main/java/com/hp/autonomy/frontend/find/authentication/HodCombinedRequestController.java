/*
 * Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.frontend.find.authentication;

import com.hp.autonomy.hod.client.api.authentication.SignedRequest;
import com.hp.autonomy.hod.client.error.HodErrorException;
import com.hp.autonomy.hod.sso.HodAuthenticationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class HodCombinedRequestController {
    @Autowired
    private HodAuthenticationRequestService tokenService;

    @RequestMapping(value = "/combined-request", method = RequestMethod.GET)
    public SignedRequest getCombinedRequest(
            @RequestParam("domain") final String domain,
            @RequestParam("application") final String application,
            @RequestParam("user-store-domain") final String userStoreDomain,
            @RequestParam("user-store-name") final String userStoreName
    ) throws HodErrorException {
        return tokenService.getCombinedRequest(domain, application, userStoreDomain, userStoreName);
    }

    @RequestMapping(value = "/list-application-request", method = RequestMethod.GET)
    public SignedRequest getListApplicationRequest() throws HodErrorException {
        return tokenService.getListApplicationRequest();
    }
}
