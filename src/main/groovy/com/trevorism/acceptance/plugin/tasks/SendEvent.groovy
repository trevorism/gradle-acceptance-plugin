package com.trevorism.acceptance.plugin.tasks

import com.trevorism.acceptance.plugin.util.ResultParser
import com.trevorism.acceptance.plugin.util.TestResult

import com.trevorism.event.EventProducer
import com.trevorism.event.PingingEventProducer
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author tbrooks
 */
class SendEvent extends DefaultTask{

    EventProducer<TestResult> producer = new PingingEventProducer<>()

    @TaskAction
    void sendEvents(){
        String directory = "${project.buildDir.path}/test-results/cucumber/acceptance"
        project.file(directory).eachFile{
            if(it.name.endsWith("feature.json")){
                parseFile(it)
            }
        }
    }

    private void parseFile(File file) {
        List<TestResult> results = ResultParser.parseResult(file.text)
        results.each { TestResult result ->
            sendEvent(result)
        }
    }

    private void sendEvent(TestResult result) {
        try {
            result.projectName = project.name
            producer.sendEvent("testresult", result)
        } catch (Exception e) {
            project.logger.error("Error sending event", e)
        }
    }

}
