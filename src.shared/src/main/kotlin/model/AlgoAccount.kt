package model

import kotlinx.serialization.Serializable

@Serializable
data class AlgoAccount(
	val address: String,
	val secret: String,
)
