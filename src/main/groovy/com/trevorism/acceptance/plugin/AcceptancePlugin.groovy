package com.trevorism.acceptance.plugin

import com.trevorism.acceptance.plugin.ext.AcceptanceTestSettings
import com.trevorism.acceptance.plugin.tasks.AcceptanceTask
import org.gradle.api.Plugin
import org.gradle.api.Project

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

        project.task("cucumber", type: AcceptanceTask) {
            group = ACCEPTANCE_GROUP
            description = "Runs cucumber tests in hte acceptance source set"
            dependsOn("assemble", project.sourceSets.acceptance.classesTaskName)
        }

        project.task("acceptance") {
            group = ACCEPTANCE_GROUP
            description = "Orchestrates acceptance tests"
            dependsOn("cucumber")
        }

    }

}
