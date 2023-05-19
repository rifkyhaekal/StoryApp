plugins {
    kotlin("android")
    id("com.android.library")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.albro.storyapp.core"
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_9
        targetCompatibility = JavaVersion.VERSION_1_9
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }
}

dependencies {
    commonAndroidLibrary()
    implementation(Dependencies.UiLibs.lottieAnimation)
    implementation(Dependencies.Networking.retrofit2)
    implementation(Dependencies.Networking.retrofitConverterMoshi)
    implementation(Dependencies.Networking.moshi)
    implementation(Dependencies.Networking.moshiKotlin)
    implementation(Dependencies.Networking.okhttp3)
    implementation(Dependencies.Networking.okhttp3Log)
    implementation(Dependencies.DataStore.dataStoreCore)
    implementation(Dependencies.DataStore.dataStorePreferences)
    implementation(Dependencies.DataStore.dataStorePreferenceCore)
    implementation(Dependencies.Paging.pagingKtx)
    implementation(Dependencies.Room.roomRuntime)
    implementation(Dependencies.Room.roomKtx)
    implementation(Dependencies.Room.roomPaging)
    kapt(Dependencies.Room.roomCompiler)
    debugImplementation(Dependencies.Testing.chuckerDebug)
    releaseImplementation(Dependencies.Testing.chuckerRelease)
}