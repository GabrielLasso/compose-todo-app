plugins {
    kotlin("multiplatform")
    id("io.ktor.plugin")
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":shared"))

                val ktorVersion = extra["ktor.version"] as String
                implementation("io.ktor:ktor-server-core:$ktorVersion")
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
            }
        }
    }
}
