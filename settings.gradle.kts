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
            version("compose", "1.1.1")
            version("coroutines", "1.6.3")
            version("serialization", "1.3.3")
            version("ktlint-gradle", "10.3.0")

            // Gradle Plugins
            plugin("kotlin.jvm", "org.jetbrains.kotlin.jvm")
                .versionRef("kotlin")
            plugin("kotlinx.serialization", "org.jetbrains.kotlin.plugin.serialization")
                .versionRef("kotlin")
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
        }
    }
}

rootProject.name = "AssetHoarder"

include(":desktop")
