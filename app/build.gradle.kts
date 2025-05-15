plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.example.neotube"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.neotube"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    hilt {
        enableAggregatingTask = false
    }
}

dependencies {
    // Kotlin + Android core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    // Compose BOM으로 버전 통일 관리
    implementation(platform(libs.androidx.compose.bom))
    // Compose UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    // Material 3
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    // Foundation (LazyColumn, Image 등)
    implementation(libs.androidx.compose.foundation)
    // Icons (optional)
    implementation(libs.androidx.compose.material.iconsExtended)
    // Navigation (if 화면 전환에 사용)
    implementation(libs.androidx.navigation.compose)
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // HTML parser
    implementation(libs.jsoup)

    // HTTP client
    implementation(libs.okhttp)

    // Media player
    implementation(libs.exoplayer.core)
    implementation(libs.exoplayer.dash)
    implementation(libs.exoplayer.database)
    implementation(libs.exoplayer.datasource)
    implementation(libs.exoplayer.hls)
    implementation(libs.exoplayer.smoothstreaming)
    implementation(libs.exoplayer.ui)
    implementation(libs.extension.mediasession)
    /** NewPipe libraries **/
    // implementation(libs.teamnewpipe.nanojson)
    implementation(libs.teamnewpipe.newpipe.extractor)
    implementation(libs.teamnewpipe.nononsense.filepicker)
    // test
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    // debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}