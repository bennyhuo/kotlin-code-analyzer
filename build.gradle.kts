plugins {
    kotlin("jvm") version "1.6.20" apply false
}

subprojects {
    group = property("GROUP").toString()
    version = property("VERSION_NAME").toString()
}