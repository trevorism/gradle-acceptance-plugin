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
            group = "verification"
            description = "Runs acceptance tests."
            ignoreFailures = true
            junitReport = true
            finalizedBy("sendAcceptanceTestEvents")
        }

        project.task("sendAcceptanceTestEvents", type: SendEvent){
            dependsOn "acceptance"
        }


    }

}
