plugins {
    kotlin("jvm")
    id("com.vanniktech.maven.publish") version "0.18.0"
}

val detektVersion = "main-SNAPSHOT"

dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-core:$detektVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-tooling:$detektVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-parser:$detektVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-utils:$detektVersion")

    api("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.8.10")

    testImplementation("junit:junit:4.12")
}