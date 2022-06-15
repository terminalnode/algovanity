rootProject.name = "algovanity"

listOf(
	"generator",
	"persistence",
	"server",
	"shared",
).forEach {
	include("src.$it")
	project(":src.$it").name = it
}
