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
            dirs ('C:\\gradle-acceptance-plugin\\build\\libs')
        }
    }
	dependencies {
        classpath ':gradle-acceptance-plugin:1.3.0'
    }
}

apply plugin: "com.trevorism.gradle.acceptance"
```

Run with:
`gradle acceptanceTest`