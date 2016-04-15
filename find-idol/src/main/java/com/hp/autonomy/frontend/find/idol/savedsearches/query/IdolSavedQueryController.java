/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.frontend.find.idol.savedsearches.query;

import com.autonomy.aci.client.services.AciErrorException;
import com.hp.autonomy.frontend.find.core.savedsearches.SavedSearchService;
import com.hp.autonomy.frontend.find.core.savedsearches.query.SavedQuery;
import com.hp.autonomy.frontend.find.core.savedsearches.query.SavedQueryController;
import com.hp.autonomy.frontend.find.core.search.QueryRestrictionsBuilder;
import com.hp.autonomy.searchcomponents.core.search.DocumentsService;
import com.hp.autonomy.searchcomponents.idol.search.IdolSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdolSavedQueryController extends SavedQueryController<String, IdolSearchResult, AciErrorException> {
    @Autowired
    public IdolSavedQueryController(final SavedSearchService<SavedQuery> service,
                                    final DocumentsService<String, IdolSearchResult, AciErrorException> documentsService,
                                    final QueryRestrictionsBuilder<String> queryRestrictionsBuilder) {
        super(service, documentsService, queryRestrictionsBuilder);
    }
}
