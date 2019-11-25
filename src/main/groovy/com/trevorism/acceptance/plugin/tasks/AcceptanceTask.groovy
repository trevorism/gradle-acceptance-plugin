package com.trevorism.acceptance.plugin.tasks

import org.gradle.api.tasks.JavaExec

class AcceptanceTask extends JavaExec {

    @Override
    String getMain() {
        return "io.cucumber.core.cli.Main"
    }

    @Override
    void exec() {
        setClasspath(project.sourceSets.acceptance.runtimeClasspath)
        args ('--plugin', "json:${project.buildDir.path}/test-results/cucumber/acceptance.json", '--glue', project.acceptanceSettings.stepDefinitionPackageRoot, "src/acceptance/${project.acceptanceSettings.featuresRoot}")
        super.exec()
    }
}
