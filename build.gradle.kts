plugins {
    kotlin("jvm") version "1.8.21" apply false
}

subprojects {
    group = property("GROUP").toString()
    version = property("VERSION_NAME").toString()
}
