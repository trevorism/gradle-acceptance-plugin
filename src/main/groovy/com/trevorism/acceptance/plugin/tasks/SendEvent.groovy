package com.trevorism.acceptance.plugin.tasks

import com.trevorism.acceptance.plugin.util.ResultParser
import com.trevorism.acceptance.plugin.util.TestResult
import com.trevorism.event.EventProducer
import com.trevorism.event.PingingEventProducer
import com.trevorism.https.DefaultInternalTokenSecureHttpClient
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author tbrooks
 */
class SendEvent extends DefaultTask{

    EventProducer<TestResult> producer = new PingingEventProducer<TestResult>(new DefaultInternalTokenSecureHttpClient())

    @TaskAction
    void sendAllEvents(){
        String resultFile = "${project.buildDir.path}/test-results/cucumber/acceptance.json"
        List<TestResult> results = ResultParser.parseResult(project.file(resultFile).text)
        results.each { TestResult result ->
            try {
                result.projectName = project.name
                producer.sendEvent("testresult", result)
            } catch (Exception e) {
                project.logger.error("Error sending event", e)
            }
        }
    }

}
