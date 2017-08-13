# gradle-acceptance-plugin

Runs cucumber acceptances tests. Sends events with test results.

Add to your build like this:
```
buildscript {
	repositories {
        mavenCentral()
        maven {
            url "https://plugins.gradle.org/m2/"
        }
        flatDir {
            dirs ('C:\\dev\\gradle-acceptance-plugin\\build\\libs', 'C:\\dev\\eventhub-client\\build\\libs', 'C:\\dev\\http-utils\\build\\libs')
        }
    }
	dependencies {
        classpath "com.commercehub:gradle-cucumber-jvm-plugin:0.11"
        classpath ':eventhub-client:1.0.0'
        classpath ':http-utils:1.0.0'
        classpath 'org.apache.httpcomponents:httpclient:4.5.3'
        classpath ':gradle-acceptance-plugin:1.0'
    }
}

apply plugin: "com.trevorism.gradle.acceptance"
```

Run with:
`gradle acceptanceTest`