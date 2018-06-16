package com.trevorism.acceptance.plugin.util

import groovy.transform.Canonical

/**
 * @author tbrooks
 */
@Canonical
class TestResult {

    String feature
    String name
    String given
    String when
    String then
    boolean passing
    int durationMillis
    String projectName
}
