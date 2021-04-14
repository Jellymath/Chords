import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.4.32"
}

group = "com.jelly"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm()
    js {
        browser()
    }
    sourceSets {
        all {
            languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
            languageSettings.useExperimentalAnnotation("kotlin.ExperimentalStdlibApi")
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.useIR = true
    kotlinOptions.jvmTarget = "11"
}

val desktopRun by jvmRunTarget("ui.swing.MainKt")
val randomConsoleRun by jvmRunTarget("console.random.MainKt")

fun jvmRunTarget(mainName: String) = tasks.creating(JavaExec::class) {
    group = "application"
    main = mainName
    kotlin {
        val main = targets["jvm"].compilations["main"]
        dependsOn(main.compileAllTaskName)
        classpath(
            { main.output.allOutputs.files },
            { configurations["jvmRuntimeClasspath"] }
        )
    }
}