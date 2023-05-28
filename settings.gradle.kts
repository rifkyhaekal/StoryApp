@file:Suppress("UnstableApiUsage")

include(":profile")


pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url =  uri("https://jitpack.io") }
    }
}

rootProject.name = "StoryApp"
include(":app")
include(":core")
include(":auth")
include(":detail-story")
include(":add-story")
include(":stories")
include(":home")
