plugins {
    kotlin("multiplatform")
    id("io.ktor.plugin")
}

kotlin {
    jvm()
    sourceSets {
        jvmMain.dependencies {
            implementation(project(":shared"))

            val ktorVersion = extra["ktor.version"] as String
            implementation("io.ktor:ktor-server-core:$ktorVersion")
            implementation("io.ktor:ktor-server-netty:$ktorVersion")
            implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
        }
    }
}
