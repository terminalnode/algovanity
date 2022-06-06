import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.6.21"

	application
	kotlin("jvm") version kotlinVersion
	id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion
	id("org.jmailen.kotlinter") version "3.10.0"
}

kotlinter {
	experimentalRules = true
	disabledRules = arrayOf(
		"experimental:argument-list-wrapping",
		"experimental:comment-wrapping",
		"experimental:annotation",
	)
}

group = "algo.terminal.algovanity"
version = "0.0.1"
application {
	mainClass.set("algo.terminal.algovanity.server.MainKt")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf()
		jvmTarget = "17"
	}
}

repositories {
	mavenCentral()
}

dependencies {
	val algoSdkVersion = "1.15.0"
	val ktorVersion = "2.0.2"
	val logbackVersion = "1.2.11"

	// Internals
	implementation(project(":generator"))
	implementation(project(":persistence"))
	implementation(project(":utils"))

	// Ktor
	implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-resources:$ktorVersion")
	implementation("io.ktor:ktor-server-cors-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
	implementation("io.ktor:ktor-serialization-gson-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-auth-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-auth-jwt-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")

	// Logging
	implementation("ch.qos.logback:logback-classic:$logbackVersion")
	implementation("org.codehaus.janino:janino:3.1.7")

	// Other
	implementation("com.algorand:algosdk:$algoSdkVersion")
}
