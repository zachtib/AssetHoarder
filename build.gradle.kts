group = "com.zachtib"
version = "1.0-SNAPSHOT"

// KTIJ-19369
// https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.ktlint) apply false
}

tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}
