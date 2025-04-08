plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinxSerialization)
}

android {
    namespace = "com.shop.main"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.shop.main"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.kotlinx.serialization.json)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.compose.lifecycle.viewmodel)
    implementation(libs.androidx.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.compose.coil)
    implementation(libs.compose.pager)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Mavericks
    implementation(libs.bundles.mavericks)
    testImplementation(libs.mavericks.testing)

    // Android Test
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // Local unit test
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test)
    testImplementation(libs.androidx.test.junit)
    testImplementation(libs.bundles.testImplementation)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)

    // Compose debug tools
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
