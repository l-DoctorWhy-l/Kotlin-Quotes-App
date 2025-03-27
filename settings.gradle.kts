pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Kotlin Quotes App"
include(":app")
include(":screens:main")
include(":screens:favourites")
include(":design")
include(":models")
include(":quotes-api")
include(":database")
include(":screens:search")
include(":navigation")
include(":screens:splash")
include(":screens:sign_in")
include(":screens:sign_up")
include(":screens:quote-details")
include(":screens:profile")
include(":screens:add-quote")
include(":utils")
include(":screens:settings")
