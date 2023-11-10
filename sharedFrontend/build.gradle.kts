plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("io.ktor.plugin") apply false
}

kotlin {
    androidTarget()

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "sharedFrontend"
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            api(project(":shared"))

            val precomposeVersion = extra["precompose.version"] as String
            api("moe.tlaster:precompose:$precomposeVersion")
            api("moe.tlaster:precompose-viewmodel:$precomposeVersion")
            api("moe.tlaster:precompose-koin:$precomposeVersion")

            val ktorVersion = extra["ktor.version"] as String
            api("io.ktor:ktor-client-core:$ktorVersion")

            val koinComposeVersion = extra["koin-compose.version"] as String
            api("io.insert-koin:koin-compose:$koinComposeVersion")
        }
        androidMain.dependencies {
            api("androidx.activity:activity-compose:1.8.0")
            api("androidx.appcompat:appcompat:1.6.1")
            api("androidx.core:core-ktx:1.12.0")

            val ktorVersion = extra["ktor.version"] as String
            api("io.ktor:ktor-client-okhttp:$ktorVersion")
        }
        iosMain.dependencies {
            val ktorVersion = extra["ktor.version"] as String
            api("io.ktor:ktor-client-darwin:$ktorVersion")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.common)

            val ktorVersion = extra["ktor.version"] as String
            implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
