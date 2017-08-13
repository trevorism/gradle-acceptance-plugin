package com.trevorism.acceptance.plugin

import com.trevorism.acceptance.plugin.tasks.SendEvent
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

/**
 * @author tbrooks
 */
class AcceptancePlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        project.apply plugin: "com.commercehub.cucumber-jvm"
        project.addCucumberSuite "acceptance"
        project.ext.sendEvents = true
        project.acceptance{
            junitReport = true
        }

        project.task("sendAcceptanceTestEvents", type: SendEvent){
            dependsOn "acceptance"
        }

        project.task("acceptanceTest", type: Test){
            group = "verification"
            description = "Runs cucumber tests."
            dependsOn "sendAcceptanceTestEvents"
        }

        if(project.ext.sendEvents)
            project.tasks."acceptance".finalizedBy("sendAcceptanceTestEvents")

    }

}
