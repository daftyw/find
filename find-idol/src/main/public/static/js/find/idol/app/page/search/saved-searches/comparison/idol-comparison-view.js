/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

define([
    'find/app/page/search/saved-searches/comparison/comparison-view',
    'find/idol/app/page/search/results/idol-results-view'
], function(ComparisonView, ResultsView) {
    'use strict';

    return ComparisonView.extend({
        ResultsView: ResultsView
    });
});
