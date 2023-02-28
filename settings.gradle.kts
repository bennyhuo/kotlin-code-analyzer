include(":code-analyzer")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
    }
}

dependencyResolutionManagement {
    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
    }
}