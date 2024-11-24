rootProject.name = "ufi"

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
    }
}

include(":intf")
include(":util")
File("fts").listFiles()?.filter(File::isDirectory)?.forEach {
    include(":fts:${it.name}")
}
