plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.services)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-parcelize")
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
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
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
    implementation(libs.coil)
    implementation(libs.accompanistPermissions)
    implementation(libs.androidx.material3.android)
    implementation(libs.cameraLifecycle)
    implementation(libs.cameraView)
    implementation(libs.cameraTwo)
    implementation(libs.lifeCycleRunTimeComposeAndroid)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.retrofit)
    implementation(libs.okHttpLoggingInterceptor)
    implementation(libs.kotlinSerializationJson)
    implementation(libs.retrofitKotlinSerialization)
    implementation(libs.okHttp)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}