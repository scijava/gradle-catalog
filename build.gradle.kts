import core.GenerateCode
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`

    // Apply the Kotlin JVM plugin to add support for Kotlin.
    kotlin("jvm") version embeddedKotlinVersion

    `maven-publish`

    `java-library`

    id("com.gradle.plugin-publish") version "1.0.0-rc-3"

    //    idea
}

group = "org.scijava"
version = "32.0.0-SNAPSHOT"

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform(kotlin("bom", embeddedKotlinVersion)))

    // Use the Kotlin JDK 8 standard library.
    implementation(kotlin("stdlib-jdk8"))

    // Use the Kotlin test library.
    testImplementation(kotlin("test"))

    // Use the Kotlin JUnit integration.
    testImplementation(kotlin("test-junit"))
}

gradlePlugin {
    // Define the plugin
    plugins.create("scijava-catalog") {
        id = "org.scijava.catalogs"
        displayName = "SciJava Gradle catalog"
        description = "Take advantage of the whole SciJava ecosystem via Gradle catalogs feature"
        implementationClass = "sciJava.SciJavaCatalogPlugin"
    }
}

pluginBundle {
    website = "https://github.com/scijava/gradle-catalog"
    vcsUrl = "https://github.com/scijava/gradle-catalog"
    tags = listOf("scijava", "gradle", "catalog")
}

//idea {
//    module {
//        // Not using += due to https://github.com/gradle/gradle/issues/8749
//        sourceDirs = sourceDirs + file("build/generated/ksp/main/kotlin") // or tasks["kspKotlin"].destination
//        testSourceDirs = testSourceDirs + file("build/generated/ksp/test/kotlin")
//        generatedSourceDirs = generatedSourceDirs + file("build/generated/ksp/main/kotlin") + file("build/generated/ksp/test/kotlin")
//    }
//}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    val generateCode by registering(GenerateCode::class)
    assemble {
        dependsOn(generateCode)
    }
}

sourceSets {
    main {
        java.srcDir("$buildDir/generated")
    }
}

// Add a source set for the functional test suite
//val functionalTestSourceSet = sourceSets.create("functionalTest") {
//}
//
//gradlePlugin.testSourceSets(functionalTestSourceSet)
//configurations.getByName("functionalTestImplementation").extendsFrom(configurations.getByName("testImplementation"))
//
//// Add a task to run the functional tests
//val functionalTest by tasks.registering(Test::class) {
//    testClassesDirs = functionalTestSourceSet.output.classesDirs
//    classpath = functionalTestSourceSet.runtimeClasspath
//}
//
//val check by tasks.getting(Task::class) {
//    // Run the functional tests as part of `check`
//    dependsOn(functionalTest)
//}
//
//publishing {
//    repositories {
//        maven {
//            url = uri("$rootDir/../mary")
//        }
//    }
//}
