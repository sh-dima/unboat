import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow)

    alias(libs.plugins.paper)
    alias(libs.plugins.paper.run)

    alias(libs.plugins.yml)
}

repositories {
    mavenCentral()
}

version = ProcessBuilder("git", "describe", "--tags", "--always", "--dirty")
    .start()
    .inputStream
    .bufferedReader()
    .readText()
    .trim()

dependencies {
    library(kotlin("stdlib"))

    implementation(libs.metrics)

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(libs.junit.jupiter.engine)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    paperweight.paperDevBundle("1.21.9-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<AbstractArchiveTask>().configureEach {
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true

    from("LICENSE")
    from("README.md")

    filePermissions {
        user.read = true
        user.write = true
        user.execute = false

        group.read = true
        group.write = false
        group.execute = false

        other.read = true
        other.write = false
        other.execute = false
    }

    dirPermissions {
        user.read = true
        user.write = true
        user.execute = true

        group.read = true
        group.write = false
        group.execute = true

        other.read = false
        other.write = false
        other.execute = true
    }
}

tasks.withType<ShadowJar>().configureEach {
    minimize()

    enableAutoRelocation = true
    relocationPrefix = "io.gitlab.shdima.unboat.dependencies"

    archiveClassifier = ""

    from("assets/text/licenses") {
        into("licenses")
    }
}

tasks.named<Jar>("jar") {
    enabled = false
}

bukkit {
    name = "Unboat"
    description = "A Minecraft Paper plugin that prevents aggravated mobs from getting stuck in boats"

    main = "io.gitlab.shdima.unboat.Unboat"
    apiVersion = "1.20"
    version = project.version.toString()

    authors = listOf(
        "Esoteric Enderman"
    )

    website = "https://gitlab.com/shdima/unboat"
}
