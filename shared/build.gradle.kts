plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            val serializationVersion = extra["serialization.version"] as String
            api("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

            val ktorVersion = extra["ktor.version"] as String
            api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            api("io.ktor:ktor-client-content-negotiation:$ktorVersion")

            val koinVersion = extra["koin.version"] as String
            api("io.insert-koin:koin-core:$koinVersion")
        }
    }
}