package feature.presentation.pokedex

import core.error.CustomError
import feature.domain.model.Pokemon

data class PokemonState(
    val isLoading: Boolean = false,
    val pokemons : List<Pokemon> = emptyList(),
    val error: CustomError? = null
)