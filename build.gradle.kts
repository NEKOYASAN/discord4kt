import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    id("io.gitlab.arturbosch.detekt") version "1.15.0" apply false
}

group = "dev.mirror-kt"
version = "0.1.0"

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    repositories {
        mavenCentral()
        maven(url = "https://kotlin.bintray.com/kotlinx/")
        jcenter()
    }

    extensions.configure<DetektExtension>("detekt") {
        config = files("config/detekt/config.yml")
        reports {
            xml.enabled = true

            txt.enabled = false
            html.enabled = false
        }
    }
}