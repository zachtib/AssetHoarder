import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// KTIJ-19369
// https://youtrack.jetbrains.com/issue/KTIJ-19369
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

    testImplementation(kotlin("test"))
    testImplementation(libs.kotlinx.coroutines.test)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "com.zachtib.assets.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "AssetHoarder"
            packageVersion = "1.0.0"
        }
    }
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
