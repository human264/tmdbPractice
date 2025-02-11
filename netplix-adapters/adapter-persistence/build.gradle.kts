dependencies {
    implementation(project(":netplix-core:core-port"))
    implementation(project(":netplix-core:core-domain"))
    implementation(project(":netplix-commons"))

    runtimeOnly(project(":netplix-core:core-service"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework:spring-tx")

    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")

    runtimeOnly("mysql:mysql-connector-java:8.0.28")

    integrationImplementation("org.springframework.boot:spring-boot-starter-test")
}