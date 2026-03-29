pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PipingIsometricDrawing"

include(":app")
include(":core:domain", ":core:data", ":core:standards")
include(":feature:drawing", ":feature:components", ":feature:bom", ":feature:export", ":feature:settings")
