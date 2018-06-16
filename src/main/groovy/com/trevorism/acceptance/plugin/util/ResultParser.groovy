package com.trevorism.acceptance.plugin.util

import groovy.json.JsonSlurper

/**
 * @author tbrooks
 */
class ResultParser {

    static final int MILLION = 1000 * 1000

    static List<TestResult> parseResult(String jsonText){
        JsonSlurper slurper = new JsonSlurper()
        def result = slurper.parseText(jsonText)[0]
        String feature = result.name
        def elements = result.elements

        elements.collect { element ->
            TestResult testResult = initalizeTestResult(element.name, feature)
            setTestResultValues(testResult, element)
            return testResult
        }
    }

    private static void setTestResultValues(TestResult testResult, element) {
        int duration = 0
        String currentStatement
        element.steps.each { step ->
            currentStatement = getCurrentStatement(step.keyword, currentStatement)

            if (step.keyword == "And ")
                testResult."${currentStatement}" += " "
            if (step.result.status == "failed") {
                testResult.passing = false
            }
            if(step.result.duration){
                duration += step.result.duration
            }
            testResult."${currentStatement}" += "${step.keyword}${step.name}"
        }
        testResult.durationMillis = (int) (duration / MILLION)
    }

    private static TestResult initalizeTestResult(String name, String feature) {
        return new TestResult(feature, name, "", "", "", true, 0)
    }

    private static String getCurrentStatement(String keyword, String existingStatement){
        if(keyword == "Given ")
            return "given"
        if(keyword == "When ")
            return "when"
        if(keyword == "Then ")
            return "then"
        return existingStatement
    }
}
