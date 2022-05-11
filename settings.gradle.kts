include(":code-analyzer")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
    }
}

dependencyResolutionManagement {
    repositories {
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
    }
}