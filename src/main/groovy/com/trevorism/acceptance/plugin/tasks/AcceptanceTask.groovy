package com.trevorism.acceptance.plugin.tasks

import org.gradle.api.tasks.JavaExec

class AcceptanceTask extends JavaExec {

    @Override
    void exec() {
        setClasspath(project.sourceSets.acceptance.runtimeClasspath)
        setMain("io.cucumber.core.cli.Main")
        args ('--plugin', "json:${project.buildDir.path}/test-results/cucumber/acceptance.json", '--glue', project.acceptanceSettings.stepDefinitionPackageRoot, "src/acceptance/${project.acceptanceSettings.featuresRoot}")
        super.exec()
    }
}
