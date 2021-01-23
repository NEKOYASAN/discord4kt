import org.apache.tools.ant.taskdefs.condition.Os

plugins {
    kotlin("multiplatform") version "1.4.21-2"
    kotlin("plugin.serialization") version "1.4.21-2"
}

dependencies {
    commonMainImplementation(kotlin("stdlib"))
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.1")
    commonMainImplementation("io.ktor:ktor-client-core:1.5.0")
    commonTestImplementation(kotlin("test"))
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js {
        nodejs()
    }
    when {
        Os.isFamily(Os.FAMILY_WINDOWS) -> {
            mingwX64()
        }
        Os.isFamily(Os.FAMILY_MAC) -> {
            macosX64()
        }
    }
    linuxX64()
}