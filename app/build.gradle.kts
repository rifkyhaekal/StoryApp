import Versions.APP_MAJOR_VERSION
import Versions.APP_MINOR_VERSION
import Versions.APP_PATCH_VERSION
import Versions.COMPILE_SDK
import Versions.MIN_SDK
import Versions.TARGET_SDK

plugins {
    kotlin("android")
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = COMPILE_SDK

    defaultConfig {
        applicationId = "com.albro.storyapp"
        minSdk = MIN_SDK
        targetSdk = TARGET_SDK
        versionCode =
            APP_MAJOR_VERSION * 100 + APP_MINOR_VERSION * 10 + APP_PATCH_VERSION
        versionName =
            "$APP_MAJOR_VERSION.$APP_MINOR_VERSION.$APP_PATCH_VERSION"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    testOptions {
        animationsDisabled = true
    }
    namespace = "com.albro.storyapp"
}

dependencies {
    testImplementation(Dependencies.Paging.pagingKtx)
    androidTestImplementation(Dependencies.Paging.pagingKtx)
    androidTestImplementation(Dependencies.Paging.pagingKtx)
    androidTestImplementation(Dependencies.Room.roomRuntime)
    androidTestImplementation(Dependencies.Networking.retrofit2)
    commonAndroidLibrary()
}