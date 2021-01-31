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

    dependencies {
        "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:1.15.0")
    }

    extensions.configure<DetektExtension>("detekt") {
        config = this@allprojects.rootProject.files("config/detekt/config.yml")
        input = files(
            (file("src").listFiles { file: File -> file.isDirectory } ?: arrayOf<File>())
                .flatMap {
                    listOf(
                        it.resolve("java"),
                        it.resolve("kotlin")
                    )
                }
        )
        reports {
            xml {
                enabled = true
                destination =
                    this@allprojects.rootProject.file("build/reports/detekt/${this@allprojects.project.name}.xml")
            }

            txt.enabled = false
            html.enabled = false
        }
    }
}