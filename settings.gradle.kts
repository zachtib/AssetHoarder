enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    versionCatalogs {
        create("libs") {
            // Dependency Versions
            version("kotlin", "1.6.10")
            version("ksp", "1.6.10-1.0.4")
            version("compose", "1.1.1")
            version("coroutines", "1.6.3")
            version("serialization", "1.3.3")
            version("ktlint-gradle", "10.3.0")
            version("turbine", "0.8.0")
            version("koin", "3.2.0")
            version("koin-ksp", "1.0.1")

            // Gradle Plugins
            plugin("kotlin.jvm", "org.jetbrains.kotlin.jvm")
                .versionRef("kotlin")
            plugin("kotlinx.serialization", "org.jetbrains.kotlin.plugin.serialization")
                .versionRef("kotlin")
            plugin("ksp", "com.google.devtools.ksp")
                .versionRef("ksp")
            plugin("compose", "org.jetbrains.compose")
                .versionRef("compose")
            plugin("ktlint", "org.jlleitschuh.gradle.ktlint")
                .versionRef("ktlint-gradle")

            // Libraries
            library("kotlinx.serialization.json", "org.jetbrains.kotlinx", "kotlinx-serialization-json")
                .versionRef("serialization")

            library("kotlinx.coroutines.core", "org.jetbrains.kotlinx", "kotlinx-coroutines-core")
                .versionRef("coroutines")
            library("kotlinx.coroutines.swing", "org.jetbrains.kotlinx", "kotlinx-coroutines-swing")
                .versionRef("coroutines")
            library("kotlinx.coroutines.test", "org.jetbrains.kotlinx", "kotlinx-coroutines-test")
                .versionRef("coroutines")

            library("koin.core", "io.insert-koin", "koin-core")
                .versionRef("koin")
            library("koin.test", "io.insert-koin", "koin-test")
                .versionRef("koin")
            library("koin.annotations", "io.insert-koin", "koin-annotations")
                .versionRef("koin-ksp")
            library("koin.compiler", "io.insert-koin", "koin-compiler")
                .versionRef("koin-ksp")

            library("turbine", "app.cash.turbine", "turbine")
                .versionRef("turbine")
        }
    }
}

rootProject.name = "AssetHoarder"

include(":desktop")
include(":library")
include(":sampleapp")
