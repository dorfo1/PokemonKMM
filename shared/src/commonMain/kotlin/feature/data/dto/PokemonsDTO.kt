package feature.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonsDTO(
    val results: List<PokemonDTO>
)