plugins {
	kotlin("jvm") version "1.6.21"
	`java-library`
	application
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
	mainClass.set("algo.terminal.algovanity.generator.MainKt")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
	mavenCentral()
}

dependencies {
	val algoSdkVersion = "1.15.0"
	val logbackVersion = "1.2.11"

	// Internals
	implementation(project(":utils"))

	// Other
	implementation("ch.qos.logback:logback-classic:$logbackVersion")
	implementation("com.algorand:algosdk:$algoSdkVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
}
