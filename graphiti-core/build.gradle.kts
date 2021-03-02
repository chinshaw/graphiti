val graphql_java_version = "14.0"

plugins {
    kotlin("jvm") version "1.4.31"
}

version = "unspecified"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk8"))


    implementation("org.postgresql:postgresql:42.2.12")
    implementation("com.graphql-java:graphql-java:${graphql_java_version}")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("log4j", "log4j", "1.2.17")

    testImplementation("org.slf4j", "slf4j-log4j12", "1.7.30")
    testImplementation("junit:junit:4.12")
}


tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}
