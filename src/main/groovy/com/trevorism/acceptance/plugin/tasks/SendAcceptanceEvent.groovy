package com.trevorism.acceptance.plugin.tasks

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.trevorism.acceptance.plugin.util.ResultParser
import com.trevorism.acceptance.plugin.util.TestEvent
import com.trevorism.acceptance.plugin.util.TestResult
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

import static groovyx.net.http.Method.POST
import static groovyx.net.http.ContentType.JSON

class SendAcceptanceEvent extends DefaultTask{

    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create()

    @TaskAction
    void sendEvent() {
        def fileProvider = project.layout.buildDirectory.file("test-results/cucumber/acceptance.json")
        String acceptanceJson = fileProvider?.get()?.getAsFile()?.text

        if(!acceptanceJson){
            logger.warn("No acceptance test results found")
            return
        }

        List<TestResult> results = new ResultParser().parseResult(acceptanceJson)
        TestEvent testEvent = new TestEvent(
                service: project.name,
                kind: "cucumber",
                success: results.every { it.passing },
                numberOfTests: results.size(),
                durationMillis: Math.abs(results.sum { it.durationMillis }),
                date: new Date()
        )

        String message = gson.toJson(testEvent)
        HTTPBuilder http = new HTTPBuilder("https://event.data.trevorism.com/event/testResult")
        http.request(POST, JSON) { req ->
            body = message
            response.success = { resp, json ->
            }
        }
    }
}
