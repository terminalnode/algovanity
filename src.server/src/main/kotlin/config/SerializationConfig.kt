package algo.terminal.algovanity.server.config

import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

fun Application.configureSerialization() {
	install(ContentNegotiation) {
		json(
			Json {
				isLenient = true
				serializersModule = SerializersModule {
					contextual(HttpStatusCode::class) { HttpStatusCodeSerializer }
				}
			}
		)
	}
}

object HttpStatusCodeSerializer : KSerializer<HttpStatusCode> {
	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("HttpStatusCode", PrimitiveKind.STRING)

	override fun deserialize(decoder: Decoder): HttpStatusCode =
		HttpStatusCode.allStatusCodes.first { it.toString() == decoder.decodeString() }

	override fun serialize(encoder: Encoder, value: HttpStatusCode) {
		encoder.encodeString(value.toString())
	}
}
