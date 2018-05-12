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

    @TaskAction
    void sendEvents(){
        EventProducer<TestResult> producer = new PingingEventProducer<>()
        String directory = "${project.buildDir.path}/test-results/cucumber/acceptance"
        project.file(directory).eachFile{
            if(it.name.endsWith("feature.json")){
                List<TestResult> results = ResultParser.parseResult(it.text)
                results.each{ TestResult result ->
                    try{
                        producer.sendEvent("testresult", result)
                    }catch (Exception e){
                        e.printStackTrace()
                    }
                }
            }
        }

    }

}
