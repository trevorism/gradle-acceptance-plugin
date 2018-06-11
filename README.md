# gradle-acceptance-plugin

Runs cucumber acceptances tests. Sends events with test results.

Add to your build like this:
```
buildscript {
	repositories {
        mavenCentral()
        maven {
            url uri("http://trevorism-build.eastus.cloudapp.azure.com/nexus/repository/maven-releases")
        }
    }
	dependencies {
        classpath 'com.trevorism:gradle-acceptance-plugin:1.3.0'
    }
}

apply plugin: "com.trevorism.gradle.acceptance"
```

Run with:
`gradle acceptanceTest`