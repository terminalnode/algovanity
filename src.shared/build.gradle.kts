plugins {
	kotlin("jvm") version "1.6.21"
	`java-library`
	kotlin("plugin.serialization") version "1.6.21"
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
	api("org.slf4j:slf4j-api:1.7.36")
	compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
}
