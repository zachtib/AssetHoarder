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

            // Gradle Plugins
            plugin("kotlin.jvm", "org.jetbrains.kotlin.jvm")
                .versionRef("kotlin")
            plugin("compose", "org.jetbrains.compose")
                .versionRef("compose")
            // Libraries
        }
    }
}

rootProject.name = "AssetHoarder"

include(":desktop")