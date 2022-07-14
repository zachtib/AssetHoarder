import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ktlint)
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.swing)

    implementation(libs.koin.core)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

ktlint {
    // This rule should be able to be re-enabled once ktlint-gradle supports
    // ktlint 0.45.0+, which added support for the .editorconfig rule:
    // ij_kotlin_packages_to_use_import_on_demand
    //
    // Check: https://github.com/JLLeitschuh/ktlint-gradle/blob/master/plugin/gradle/libs.versions.toml
    // issue: https://github.com/JLLeitschuh/ktlint-gradle/issues/589
    disabledRules.set(setOf("no-wildcard-imports"))
}
