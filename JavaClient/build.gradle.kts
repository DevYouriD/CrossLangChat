
// Dependency versions
val junitVersion = "5.12.1"
val slf4jVersion = "2.0.17"
val signalRVersion = "10.0.0-preview.2.25164.1"

plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // SignalR uses Log4j
    implementation("org.slf4j:slf4j-jdk14:$slf4jVersion")

    // We use SignalR to connect to the dotnet infrastructure
    implementation("com.microsoft.signalr:signalr:$signalRVersion")
}

tasks.test {
    useJUnitPlatform()
}