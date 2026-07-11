import java.util.Base64
import java.util.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.google.devtools.ksp)
  alias(libs.plugins.roborazzi)
  alias(libs.plugins.secrets)
  
  alias(libs.plugins.google.services)
  alias(libs.plugins.google.crashlytics)
  alias(libs.plugins.google.perf)
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}


val keystoreBase64 = run {
    var key = System.getenv("DEBUG_KEYSTORE_BASE64")
    if (key.isNullOrBlank()) {
        val envFile = file("${rootDir}/.env")
        if (envFile.exists()) {
            val properties = Properties()
            envFile.inputStream().use { properties.load(it) }
            key = properties.getProperty("DEBUG_KEYSTORE_BASE64")
        }
    }
    if (key.isNullOrBlank()) {
        "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"
    } else {
        key
    }
}
val debugKeystoreFile = file("${rootDir}/debug.keystore")
if (!debugKeystoreFile.exists()) {
    debugKeystoreFile.writeBytes(Base64.getDecoder().decode(keystoreBase64))
}

android {
  namespace = "com.example"
  compileSdk { version = release(36) { minorApiLevel = 1 } }

  val customCode = project.findProperty("customVersionCode")?.toString()?.toIntOrNull()
  val customName = project.findProperty("customVersionName")?.toString()

  defaultConfig {
    applicationId = "com.aistudio.weeklyfinance.khcrwt"
    minSdk = 26
    targetSdk = 36
    versionCode = customCode ?: 100
    versionName = customName ?: "10.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  signingConfigs {
    create("release") {
      storeFile = file("${rootDir}/debug.keystore")
      storePassword = "android"
      keyAlias = "androiddebugkey"
      keyPassword = "android"
    }
    create("debugConfig") {
      storeFile = file("${rootDir}/debug.keystore")
      storePassword = "android"
      keyAlias = "androiddebugkey"
      keyPassword = "android"
    }
  }

  lint {
    abortOnError = false
  }

  buildTypes {
    release {
      isCrunchPngs = false
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      signingConfig = signingConfigs.getByName("release")
    }
    debug {
      signingConfig = signingConfigs.getByName("debugConfig")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  testOptions { unitTests { isIncludeAndroidResources = true } }
}

secrets {
  propertiesFileName = ".env"
  defaultPropertiesFileName = ".env.example"
}

dependencies {
  implementation("androidx.security:security-crypto:1.1.0-alpha06")
  implementation(platform(libs.androidx.compose.bom))
  implementation(platform(libs.firebase.bom))
  implementation(libs.firebase.analytics)
  implementation(libs.firebase.crashlytics)
  implementation(libs.firebase.perf)
  implementation(libs.firebase.appcheck)
  implementation("com.google.firebase:firebase-database")
  implementation("com.google.firebase:firebase-auth")
  implementation("com.google.firebase:firebase-inappmessaging-display")
  implementation("com.google.firebase:firebase-messaging")
  implementation("com.google.firebase:firebase-config")
  implementation("androidx.credentials:credentials:1.2.2")
  implementation("androidx.credentials:credentials-play-services-auth:1.2.2")
  implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.material.icons.core)
  implementation(libs.androidx.compose.material.icons.extended)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.work.runtime.ktx)
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  implementation(libs.androidx.room.ktx)
  implementation(libs.androidx.room.runtime)
  implementation("net.zetetic:android-database-sqlcipher:4.5.4")
  implementation(libs.coil.compose)
  implementation(libs.converter.moshi)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.logging.interceptor)
  implementation(libs.moshi.kotlin)
  implementation(libs.okhttp)
  implementation(libs.play.services.auth)
  implementation(libs.retrofit)
  testImplementation(libs.androidx.compose.ui.test.junit4)
  testImplementation(libs.androidx.core)
  testImplementation(libs.androidx.junit)
  testImplementation(libs.junit)
  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.robolectric)
  testImplementation(libs.roborazzi)
  testImplementation(libs.roborazzi.compose)
  testImplementation(libs.roborazzi.junit.rule)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.runner)
  debugImplementation(libs.androidx.compose.ui.test.manifest)
  debugImplementation(libs.androidx.compose.ui.tooling)
  "ksp"(libs.androidx.room.compiler)
  "ksp"(libs.moshi.kotlin.codegen)
}
