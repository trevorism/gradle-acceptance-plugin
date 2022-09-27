# gradle-acceptance-plugin
![Build](https://github.com/trevorism/gradle-acceptance-plugin/actions/workflows/build.yml/badge.svg)
![GitHub last commit](https://img.shields.io/github/last-commit/trevorism/gradle-acceptance-plugin)
![GitHub language count](https://img.shields.io/github/languages/count/trevorism/gradle-acceptance-plugin)
![GitHub top language](https://img.shields.io/github/languages/top/trevorism/gradle-acceptance-plugin)


Runs cucumber acceptances tests. Compatible with gradle 6 and io.cucumber:cucumber-groovy:4.7.x

Current [Version](https://github.com/trevorism/gradle-acceptance-plugin/releases/latest)


Create a project with an src/acceptance folder. Feature files (*.feature) go in src/acceptance/resources/features. 
Step definitions belong in src/acceptance/groovy/<package name>


Add to your build like this:
```
buildscript {
	repositories {
        mavenCentral()
        maven {
            url uri("https://maven.pkg.github.com/trevorism/gradle-release-plugin")
            credentials {
                username = findProperty("github.user") ?: System.getenv("GITHUB_ACTOR")
                password = findProperty("github.token") ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
	dependencies {
        classpath 'com.trevorism:gradle-acceptance-plugin:2.2.0'
    }
}

apply plugin: "com.trevorism.gradle.acceptance"

//The below settings are the defaults -- override these values if necessary
acceptanceSettings{
    stepDefinitionPackageRoot = "com/trevorism/gcloud"
    featuresRoot = "resources/features"
}

dependencies{
    acceptanceImplementation 'io.cucumber:cucumber-groovy:4.7.1'
    acceptanceImplementation 'com.trevorism:http-utils:1.0.2'
    acceptanceImplementation 'org.apache.httpcomponents:httpclient:4.5.10'
}

```

Run with:
`gradle acceptance`