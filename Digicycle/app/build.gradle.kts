plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.digicycle"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.digicycle"

        // 👇 PERBAIKAN 1: UBAH INI KE 26
        // Jangan 34, nanti tidak bisa jalan di HP orang lain
        minSdk = 26

        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    // 👇 PERBAIKAN 2: TAMBAHKAN INI
    // (Agar semua binding.etEmail, binding.btnRegister, dll bisa jalan)
    buildFeatures {
        viewBinding = true
    }
}

// 👇 PERBAIKAN 3: TAMBAHKAN SEMUA LIBRARY YANG HILANG
dependencies {

    // 👇 PERBAIKAN: Ganti 'libs.' dengan versi spesifik yang stabil
    implementation("androidx.core:core-ktx:1.13.1")       // Versi stabil untuk SDK 34
    implementation("androidx.activity:activity-ktx:1.9.0") // Versi stabil untuk SDK 34

    // UI (Sisanya boleh pakai libs atau versi spesifik)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // ViewModel & Lifecycle (Untuk ViewModel dan lifecycleScope)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1")

    // Retrofit (Untuk Koneksi API)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Coroutines (Untuk 'suspend fun' dan 'viewModelScope')
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // Security Crypto (Untuk UserManager)
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.viewpager2)
    implementation(libs.kotlinx.coroutines.android)
}