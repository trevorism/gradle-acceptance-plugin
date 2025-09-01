package com.trevorism.acceptance.plugin.tasks

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.trevorism.acceptance.plugin.util.ResultParser
import com.trevorism.acceptance.plugin.util.TestEvent
import com.trevorism.acceptance.plugin.util.TestResult
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.core5.http.ContentType
import org.apache.hc.core5.http.io.entity.StringEntity
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class SendAcceptanceEvent extends DefaultTask {

    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create()

    @TaskAction
    void sendEvent() {
        def fileProvider = project.layout.buildDirectory.file("test-results/cucumber/acceptance.json")
        String acceptanceJson = fileProvider?.get()?.getAsFile()?.text

        if (!acceptanceJson) {
            logger.warn("No acceptance test results found")
            return
        }

        try {
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
            CloseableHttpClient httpClient = HttpClients.createDefault()
            def post = new HttpPost("https://event.data.trevorism.com/event/testResult")
            post.setEntity(new StringEntity(message, ContentType.APPLICATION_JSON))
            def response = httpClient.execute(post, new BasicHttpClientResponseHandler())
            logger.info("Response from event service: $response")
        } catch (Exception e) {
            logger.warn("Failed to send acceptance test results: ${e.message}")
        }
    }
}
