import org.gradle.jvm.tasks.Jar

plugins {
    kotlin("jvm") version "1.4.31"
    application
}

version = "unspecified"

repositories {
    mavenCentral()
    jcenter()
}

val ktor_version = "1.3.1"
val graphiti_java_version = "14.0"

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project("::graphiti-core"))
    implementation("com.graphql-java:graphql-java:${graphiti_java_version}")

    // BAD IDEA REMOVE ME
    implementation("org.postgresql:postgresql:42.2.12")

    implementation("io.ktor:ktor-server-core:${ktor_version}")
    implementation("io.ktor:ktor-server-netty:${ktor_version}")
    implementation("io.ktor:ktor-jackson:${ktor_version}")


}

val fatJar = task("fatJar", type = org.gradle.jvm.tasks.Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Main-Class"] = "io.ktor.server.netty.EngineMain"
    }
    from(configurations.runtimeClasspath.get().map {
        if (it.isDirectory) it else zipTree(it)
    })
    with(tasks.jar.get() as CopySpec)
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
    build {
        dependsOn(fatJar)
    }
}
