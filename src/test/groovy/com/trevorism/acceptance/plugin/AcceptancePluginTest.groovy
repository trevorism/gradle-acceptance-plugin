package com.trevorism.acceptance.plugin

import org.gradle.api.Project
import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder

class AcceptancePluginTest {

    @Test
    void testApply() {
        Project project = newProject()
        assert project.tasks.findByPath("cucumber")
        assert project.tasks.findByPath("acceptance")
    }

    static Project newProject(){
        Project project = ProjectBuilder.builder().withName("foo").build()
        project.with {
            apply plugin: "com.trevorism.gradle.acceptance"
        }
        return project
    }
}
