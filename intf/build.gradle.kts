import g.bs.BuildCount
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.jar.Attributes

plugins {
    kotlin("jvm")
}

// <editor-fold desc="Build Count">
val run = BuildCount(project, "run")

val runCount = tasks.register("runCount") {
    group = "buildCount"
    doLast {
        run.inc()
    }
}

val pak = BuildCount(project, "pak")

val pakCount = tasks.register("pakCount") {
    group = "buildCount"
    doLast {
        pak.inc()
    }
}
// </editor-fold>

tasks.withType<KotlinCompile> {
    dependsOn(runCount)
}

tasks.withType<Jar> {
    dependsOn(pakCount)
    version = "1.0.${pak.read()}.${run.read()}"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes(
            Attributes.Name.IMPLEMENTATION_VENDOR.toString() to "Geno1024",
            Attributes.Name.CLASS_PATH.toString() to configurations.runtimeClasspath.get().joinToString(" ") { "file:///NH2Publish/lib/${it.name}" }
        )
    }
}
