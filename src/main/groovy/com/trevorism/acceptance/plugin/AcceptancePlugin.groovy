package com.trevorism.acceptance.plugin

import com.trevorism.acceptance.plugin.ext.AcceptanceTestSettings

import com.trevorism.acceptance.plugin.tasks.SendAcceptanceEvent
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.JavaExec

/**
 * @author tbrooks
 */
class AcceptancePlugin implements Plugin<Project> {

    private static final String ACCEPTANCE_GROUP = "acceptance"

    @Override
    void apply(Project project) {
        project.apply plugin: 'groovy'
        project.extensions.create("acceptanceSettings", AcceptanceTestSettings)
        project.sourceSets {
            acceptance {
                groovy {
                    srcDirs = ['src/acceptance/groovy']
                }
                resources {
                    srcDirs = ['src/acceptance/resources']
                }
            }
        }
        project.sourceSets.acceptance.compileClasspath += project.sourceSets.main.output
        project.sourceSets.acceptance.compileClasspath += project.sourceSets.main.compileClasspath
        project.sourceSets.acceptance.runtimeClasspath = project.sourceSets.acceptance.output + project.sourceSets.acceptance.compileClasspath

        project.task("cucumber", type: JavaExec) {
            group = ACCEPTANCE_GROUP
            description = "Runs cucumber tests in hte acceptance source set"
            classpath = project.sourceSets.acceptance.runtimeClasspath
            mainClass = "io.cucumber.core.cli.Main"
            args('--plugin', "json:${project.buildDir.path}/test-results/cucumber/acceptance.json", '--glue', project.acceptanceSettings.stepDefinitionPackageRoot, "src/acceptance/${project.acceptanceSettings.featuresRoot}")
            dependsOn("assemble", project.sourceSets.acceptance.classesTaskName)
            finalizedBy("sendAcceptanceEvent")
        }

        project.task("sendAcceptanceEvent", type: SendAcceptanceEvent) {
            group = ACCEPTANCE_GROUP
            description = "Sends acceptance test result event"

            onlyIf {
                project.gradle.startParameter.taskNames.contains("acceptance")
            }
        }

        project.task("acceptance") {
            group = ACCEPTANCE_GROUP
            description = "Orchestrates acceptance tests"
            dependsOn("cucumber")
        }


    }

}
