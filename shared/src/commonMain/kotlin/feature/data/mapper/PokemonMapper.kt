package feature.data.mapper

import feature.data.dto.PokemonDTO
import feature.domain.model.Pokemon
import kotlinx.serialization.json.jsonObject

internal fun PokemonDTO.toPokemon(): Pokemon = Pokemon(
    id = id,
    name = name,
    number = idToNumber(),
    thumbnail = spriteToThumbnail(),
)

private fun PokemonDTO.idToNumber(): String {
    return when{
        id in 10..99 -> "0$id"
        id >= 100 -> id.toString()
        else -> "00$id"
    }
}

private fun PokemonDTO.spriteToThumbnail(): String {
    return sprites["other"]?.jsonObject?.get("official-artwork")?.jsonObject?.getValue("front_default").toString()
}
