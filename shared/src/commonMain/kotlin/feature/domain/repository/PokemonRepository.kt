package feature.domain.repository

import feature.data.dto.PokemonDTO

interface PokemonRepository {

    suspend fun fetchPokemons() : List<PokemonDTO>
}