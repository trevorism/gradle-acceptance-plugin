package com.trevorism.acceptance.plugin

import com.trevorism.acceptance.plugin.tasks.SendEvent
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

/**
 * @author tbrooks
 */
class AcceptancePluginTest {

    @Test
    void testApply() {
        Project project = newProject()
        assert project.tasks.findByPath("sendAcceptanceTestEvents") instanceof SendEvent
        assert project.tasks.findByPath("acceptance")
    }

    static Project newProject(){
        Project project = ProjectBuilder.builder().withName("foo").build()
        project.with {
            buildscript {
                repositories {
                    maven {
                        url "https://plugins.gradle.org/m2/"
                    }
                }
                dependencies {
                    classpath "com.commercehub:gradle-cucumber-jvm-plugin:0.13"
                }
            }

            apply plugin: "com.trevorism.gradle.acceptance"
        }
        return project
    }
}
