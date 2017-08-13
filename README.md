# gradle-acceptance-plugin

Runs cucumber acceptances tests. Sends events with test results.

Add to your build like this:
```
buildscript {
    repositories {
        maven {
            url "https://plugins.gradle.org/m2/"
        }
    }
    dependencies {
        classpath "com.commercehub:gradle-cucumber-jvm-plugin:0.11"
    }
}

apply plugin: "com.trevorism.gradle.acceptance"
```

Run with:
`gradle acceptanceTest`