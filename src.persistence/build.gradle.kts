plugins {
	kotlin("jvm") version "1.6.21"
	`java-library`
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

repositories {
	mavenCentral()
}

dependencies {
	// Align versions of all Kotlin components
	implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Internals
	implementation(project(":shared"))

	// Exposed
	val exposedVersion = "0.38.2"
	implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")
}
