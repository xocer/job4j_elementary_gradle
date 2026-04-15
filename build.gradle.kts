plugins {
    java
    checkstyle
}

group = "ru.job4j"
version = "1.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.junit.vintage:junit-vintage-engine:5.9.0")
    testImplementation("org.assertj:assertj-core:3.23.1")

    checkstyle("com.puppycrawl.tools:checkstyle:10.3.3")
}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "10.3.3"
    configFile = file("checkstyle.xml")
    isIgnoreFailures = false
}

tasks.withType<Checkstyle>().configureEach {
    isShowViolations = true
}

tasks.check {
    dependsOn(tasks.checkstyleMain)
    dependsOn(tasks.checkstyleTest)
}