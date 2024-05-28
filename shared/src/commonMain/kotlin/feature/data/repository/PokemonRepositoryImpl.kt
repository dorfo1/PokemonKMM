package feature.data.repository

import feature.data.dto.PokemonDTO
import feature.data.remote.PokemonRemoteDataSource
import feature.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override suspend fun fetchPokemons(): List<PokemonDTO> = remoteDataSource.fetchPokemons()
}