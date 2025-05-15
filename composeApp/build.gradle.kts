import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.kotlinCocoapods)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
    cocoapods {
        version    = "1.0.0"
        summary    = "Módulo compartido con Compose Multiplatform"
        homepage   = "https://github.com/dcuestaprieto/PokemonStab"
        name       = "ComposeApp"

        // ── Opcionales ──
        authors = "Tu Nombre <dcuestaprieto@gmail.com>"
        license = "{ :type => 'MIT', :text => 'License text'}"
        source = mapOf("git" to homepage, "tag" to version).toString()

        // ── Framework y extras ──
        framework {
            baseName = "ComposeApp"
            isStatic = false
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            transitiveExport = true
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            //motor para las peticiones especifico de android
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.navigation.compose)

            implementation(libs.koin.compose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.negotiation)
            implementation(libs.kotlin.serialization)

            implementation(libs.viewmodel.compose)

            //coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor2)
            implementation(libs.coil.network.ktor3)
            api(compose.materialIconsExtended)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation("androidx.performance:performance-annotation:1.0.0-alpha01")
        }
    }
}

android {
    namespace = "org.dcuestaprieto.pokemonstab"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.dcuestaprieto.pokemonstab"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}
//./gradlew :composeApp:embedAndSignAppleFrameworkForXcode
///Applications/Xcode.app/Contents/Developer/usr/bin/xcodebuild -workspace /Users/miarey/StudioProjects/PokemonStab/iosApp/iosApp.xcworkspace -scheme iosApp -configuration Debug OBJROOT=/Users/miarey/StudioProjects/PokemonStab/build/ios SYMROOT=/Users/miarey/StudioProjects/PokemonStab/build/ios -destination id=511A1D0D-104A-4F77-8302-4F7E0C58A7ED -allowProvisioningDeviceRegistration -allowProvisioningUpdates
//generar shared.podspec luego de añadir cocoapods al gradle
//./gradlew :composeApp:podspec
//abrir emulador movil
//open -a Simulator.app
//falla por esto:
/*
error: Could not delete '/Users/miarey/StudioProjects/PokemonStab/composeApp/build/kotlin-multiplatform-resources/resources-from-dependencies/iosArm64/composeResources'

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':composeApp:iosArm64ResolveResourcesFromDependencies'.
> java.io.IOException: Could not delete '/Users/miarey/StudioProjects/PokemonStab/composeApp/build/kotlin-multiplatform-resources/resources-from-dependencies/iosArm64/composeResources'

*
 */

