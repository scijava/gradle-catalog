/*
 * This file was generated by the Gradle 'init' task.
 *
 * The settings file is used to specify which projects to include in your build.
 *
 * Detailed information about configuring a multi-project build in Gradle can be found
 * in the user manual at https://docs.gradle.org/6.6.1/userguide/multi_project_builds.html
 */

rootProject.name = "sciJava-catalogs"

dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include("processor", "annotations")
//enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")