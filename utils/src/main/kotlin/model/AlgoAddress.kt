package model

import kotlinx.serialization.Serializable

@Serializable
data class AlgoAddress(
	val address: String,
	val secret: String,
)
