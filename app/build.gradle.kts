

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")

//    id("kotlin-kapt") version "1.9.0"

//    id ("com.google.devtools.ksp")
//    kotlin("kapt") version "1.9.0"
}

android {
    namespace = "william.LETRAS_jogo_da_velha"
    compileSdk = 34

    defaultConfig {
        applicationId = "william.LETRAS_jogo_da_velha"
        minSdk = 28
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding{
        enable = true
    }
}

dependencies {

    //KOIN - INJEÇÃO DE DEPENDÊNCIAS
//    implementation("io.insert-koin:koin-android-viewmodel:3.5.0")
    implementation ("io.insert-koin:koin-android:3.5.0")


    //VIEW MODEL
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    //ROOM
    implementation("androidx.room:room-runtime:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}