repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":datamodel"))
    implementation(project(":persistence"))
    implementation(project(":service"))
    testImplementation("junit:junit:4.13.1")
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    this.archiveFileName.set("${rootProject.name}.${archiveExtension.get()}")
}
