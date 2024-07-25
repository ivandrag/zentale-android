plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.bedtime.stories.kids.zentale"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bedtime.stories.kids.zentale"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.compose.activity)
    implementation(libs.compose.navigation)
    implementation(libs.lottie)
    implementation(platform(libs.firebase.bom))
    implementation(libs.koinCore)
    implementation(libs.koinAndroid)
    implementation(libs.koinCompose)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.googleServicesAuth)
    implementation(libs.oneTapCompose)
    implementation(libs.materialIconsExtended)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}