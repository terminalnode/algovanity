package algo.terminal.algovanity.server.service.db

enum class DbCreationPolicy {
	Create,
	DropCreate,
	None,
	;

	companion object {
		fun fromProperty(property: String) = when (property.lowercase()) {
			Create.toString().lowercase() -> Create
			DropCreate.toString().lowercase() -> DropCreate
			None.toString().lowercase() -> None
			else -> throw IllegalStateException("Invalid DbCreationPolicy: $property")
		}
	}
}
