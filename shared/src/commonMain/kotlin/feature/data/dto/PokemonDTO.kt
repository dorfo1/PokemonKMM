package feature.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class PokemonDTO(
    val id: Int,
    val name: String,
    val sprites: JsonObject,
)