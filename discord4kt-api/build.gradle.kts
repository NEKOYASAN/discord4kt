import org.apache.tools.ant.taskdefs.condition.Os

plugins {
    kotlin("multiplatform") version Dependencies.Kotlin.version
    kotlin("plugin.serialization") version Dependencies.Kotlin.version
}

dependencies {
    commonMainImplementation(kotlin("stdlib"))
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Dependencies.Serialization.version}")
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-datetime:${Dependencies.DateTime.version}")
    commonMainImplementation("io.ktor:ktor-client-core:${Dependencies.Ktor.version}")
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