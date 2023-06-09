buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.2")
        classpath("com.android.tools.build:gradle:8.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_VERSION}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_VERSION}")
    }
    repositories {
        mavenCentral()
    }
}

plugins {
    id("com.android.application") version "8.0.1" apply false
    id("com.android.library") version "8.0.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("com.autonomousapps.dependency-analysis") version "1.1.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}