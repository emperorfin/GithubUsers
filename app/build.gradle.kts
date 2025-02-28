import java.util.*

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.google.dagger.hilt.android)
}

val properties = Properties().apply {
    rootProject.file("local.properties").reader().use(::load)
}
val propGitHubApiKey = properties["github.apikey"] as String

android {
    namespace = "emperorfin.android.githubusers"
    compileSdk = 34

    defaultConfig {
        applicationId = "emperorfin.android.githubusers"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "GITHUB_BASE_URL", "\"https://api.github.com\"") // TODO: May be this should be in the local.properties file.
        buildConfigField("String", "GITHUB_API_KEY", propGitHubApiKey)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true

        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.foundation)
    implementation(libs.androidx.foundation.layout)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.tracing)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.google.gson)
    implementation(libs.google.dagger.hilt.android)
    kapt(libs.google.dagger.hilt.compiler)
    implementation(libs.google.accompanist.insets)
    implementation(libs.google.accompanist.flowlayout)
    implementation(libs.github.madrapps.plot)
    implementation(libs.github.skydoves.sandwich)
    implementation(libs.github.skydoves.landscapist.coil)
    implementation(libs.github.skydoves.orchestra.balloon)
    implementation(libs.github.skydoves.whatif)
    implementation(libs.coil.compose)
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.converter.moshi)
    implementation(libs.squareup.retrofit.converter.gson)
    implementation(libs.squareup.okhttp3.logging.interceptor)

    testImplementation(libs.androidx.test.core.ktx)
    // Testing code should not be included in the main code.
    // Once https://issuetracker.google.com/128612536 is fixed this can be fixed.
    implementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.ext.junit.ktx)
    testImplementation(libs.androidx.arch.core.testing)
    testImplementation(libs.jetbrains.kotlinx.coroutines.test)
    testImplementation(libs.google.dagger.hilt.android.testing)
    kaptTest(libs.google.dagger.hilt.compiler)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.core)

    androidTestImplementation(libs.google.dagger.hilt.android.testing)
    kaptAndroidTest(libs.google.dagger.hilt.compiler)
    androidTestImplementation(libs.google.truth)
    androidTestImplementation(libs.mockito.kotlin)
    androidTestImplementation(libs.mockito.android)

    debugImplementation(platform(libs.androidx.compose.bom))
}