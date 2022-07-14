import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ktlint)
}

dependencies {
    implementation(projects.library)

    implementation(kotlin("stdlib-jdk8"))
    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.swing)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

compose.desktop {
    application {
        mainClass = "com.zachtib.sampleapp.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Sample App"
            packageVersion = "1.0.0"
        }
    }
}
