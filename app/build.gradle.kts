plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.tripmate"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tripmate"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

//repositories {
//    maven(url = "https://jitpack.io")
//}

dependencies {
// Core AndroidX Libraries
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-ktx:1.9.3")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Google Material Design Components
    implementation("com.google.android.material:material:1.12.0")

    // Firebase Authentication
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")

    // Google Sign-In
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // AI and Generative AI API
    implementation("com.google.ai.client.generativeai:generativeai:0.4.0")

    // JSON Parsing with Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Networking - Retrofit for API Calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Image Loading - Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation(libs.androidx.activity)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.firebase.database)
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    // Testing Dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

//    //animatedsvg
//    implementation("com.github.LottieFiles:dotlottie-android:0.0.3")
//    implementation("com.github.LottieFiles:dotlottie-android:0.5.0")
    implementation("com.airbnb.android:lottie:6.4.0")
    implementation(platform("com.google.firebase:firebase-bom:33.12.0"))
    implementation("com.google.firebase:firebase-database-ktx")


}