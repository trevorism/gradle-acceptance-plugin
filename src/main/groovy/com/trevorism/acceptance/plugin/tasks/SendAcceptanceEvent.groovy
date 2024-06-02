package com.trevorism.acceptance.plugin.tasks

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.trevorism.acceptance.plugin.util.ResultParser
import com.trevorism.acceptance.plugin.util.TestEvent
import com.trevorism.acceptance.plugin.util.TestResult
import com.trevorism.acceptance.plugin.util.TokenRequest
import groovyx.net.http.HTTPBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.POST

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
            String token = fetchAppToken()
            sendTestEvent(acceptanceJson, token)
        } catch (Exception e) {
            logger.warn("Failed to send acceptance test results: ${e.message}")
        }
    }

    private void sendTestEvent(String acceptanceJson, String token) {
        TestEvent testEvent = createTextEvent(acceptanceJson)
        String message = gson.toJson(testEvent)
        HTTPBuilder http = new HTTPBuilder("https://event.data.trevorism.com/event/testResult")
        http.request(POST, JSON) { req ->
            body = message
            headers.'Authorization' = "Bearer $token"
            response.success = { resp, json ->
            }
        }
    }

    String fetchAppToken() {
        try{
            HTTPBuilder http = new HTTPBuilder("https://auth.trevorism.com/token")
            Properties properties = new Properties()
            properties.load(new FileInputStream(project.layout.projectDirectory.file("src/acceptance/resources/secrets.properties").getAsFile()))
            String username = properties.get("clientId")
            String password = properties.get("clientSecret")
            TokenRequest tokenRequest = new TokenRequest(id: username, password: password)
            String message = gson.toJson(tokenRequest)
            String result = ""
            http.request(POST, JSON) { req ->
                body = message
                response.success = { resp, json ->
                    result = json
                }
            }
            return result
        }catch(Exception ignored){
            logger.warn("Unable to fetch auth token from secrets.properties file.")
            return ""
        }

    }

    private TestEvent createTextEvent(String acceptanceJson) {
        List<TestResult> results = new ResultParser().parseResult(acceptanceJson)
        return new TestEvent(
                service: project.name,
                kind: "cucumber",
                success: results.every { it.passing },
                numberOfTests: results.size(),
                durationMillis: Math.abs(results.sum { it.durationMillis }),
                date: new Date()
        )
    }
}
