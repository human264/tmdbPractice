dependencies {
    implementation(project(":netplix-core:core-port"))
    runtimeOnly(project(":netplix-core:core-service"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework:spring-tx")

    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")

    runtimeOnly("com.mysql:mysql-connector-j")


    integrationImplementation("org.springframework.boot:spring-boot-starter-test")
}