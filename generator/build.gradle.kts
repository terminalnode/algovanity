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
	val algoSdkVersion = "1.15.0"
	implementation("com.algorand:algosdk:$algoSdkVersion")
	implementation(project(":utils"))
}
