plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    `maven-publish`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
}

publishing {
    publications {
        register("release", MavenPublication::class) {
            from(components["kotlin"])

            groupId = "com.github.sodp5"
            artifactId = "unit-adapter"
            version = "1.0.1"
        }
    }
}
