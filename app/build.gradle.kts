plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.navigation.plugin)
    id("kotlin-parcelize")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt.plugin)
}

android {
    signingConfigs {
        create("config") {
            keyAlias = "anvipus"
            keyPassword = "douven77"
            storeFile = file("./anvipus.jks")
            storePassword = "douven77"
            isV2SigningEnabled = true
        }
    }
    namespace = "com.anvipus.explore"
    compileSdk = Integer.parseInt(libs.versions.compile.get())

    defaultConfig {
        applicationId = "com.anvipus.explore"
        minSdk = Integer.parseInt(libs.versions.minimum.get())
        targetSdk = Integer.parseInt(libs.versions.target.get())
        versionCode = Integer.parseInt(libs.versions.version.code.get())
        versionName = libs.versions.version.name.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            val KUNCI_GARAM: String by project
            val BASE_URL: String by project
            val API_KEY: String by project
            isMinifyEnabled = false
            buildConfigField("String", "KUNCI_GARAM", KUNCI_GARAM)
            buildConfigField("String", "BASE_URL", BASE_URL)
            buildConfigField("String", "API_KEY", API_KEY)
            resValue("string", "app_name_config", "DEV Android Explore")
            signingConfig = signingConfigs.getByName("config")
        }
        release {
            val KUNCI_GARAM: String by project
            val BASE_URL: String by project
            val API_KEY: String by project
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", BASE_URL)
            buildConfigField("String", "KUNCI_GARAM", KUNCI_GARAM)
            buildConfigField("String", "API_KEY", API_KEY)
            resValue("string", "app_name_config", "Android Explore")
            signingConfig = signingConfigs.getByName("config")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    dataBinding {
        enable = true
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    kapt {
        correctErrorTypes = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/DEPENDENCIES.txt"
            excludes += "/META-INF/LICENSE.txt"
            excludes += "/META-INF/NOTICE.txt"
            excludes += "/META-INF/NOTICE"
            excludes += "/META-INF/LICENSE"
            excludes += "/META-INF/DEPENDENCIES"
            excludes += "/META-INF/license.txt"
        }
    }
}

dependencies {
    api(project(":core"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(files("libs/YouTubeAndroidPlayerApi.jar"))
    implementation(libs.transport.runtime)

    implementation(libs.dagger.hilt)
    implementation(libs.androidx.runtime.livedata)
    ksp(libs.dagger.hilt.kapt)
    implementation(libs.hilt.navigation)


    ksp(libs.moshi.kotlin.codegen.kapt)
    ksp(libs.room.compiler)
    kapt(libs.lifecycle.common.java8.kapt)



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}