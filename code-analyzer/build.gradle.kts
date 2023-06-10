plugins {
    kotlin("jvm")
    id("com.vanniktech.maven.publish") version "0.18.0"
}

val detektVersion = "1.23.0"

dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-core:$detektVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-tooling:$detektVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-parser:$detektVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-utils:$detektVersion")

    api("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.8.21")

    testImplementation("junit:junit:4.12")
}
