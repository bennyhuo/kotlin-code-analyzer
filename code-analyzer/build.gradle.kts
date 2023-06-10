plugins {
    kotlin("jvm")
    id("com.vanniktech.maven.publish") version "0.18.0"
}

dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-core:1.20.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-tooling:1.20.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-parser:1.20.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-utils:1.20.0")

    testImplementation("junit:junit:4.12")
}