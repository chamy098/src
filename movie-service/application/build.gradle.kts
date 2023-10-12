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