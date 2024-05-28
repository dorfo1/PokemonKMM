package feature.domain.usecase

import core.error.CustomError
import core.utils.DataResult
import core.utils.UseCase
import feature.data.mapper.toPokemon
import feature.domain.model.Pokemon
import feature.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FetchPokemonsUseCase(
    private val repository: PokemonRepository
) : UseCase<Unit, List<Pokemon>, CustomError> {

    override fun invoke(params: Unit): Flow<DataResult<List<Pokemon>, CustomError>> =
        flow<DataResult<List<Pokemon>, CustomError>> {
            val response = repository.fetchPokemons()
            val pokemons = response.map { it.toPokemon() }
            emit(DataResult.Success(pokemons))
        }.catch {
            println(it)
            emit(DataResult.Error(CustomError.Generic))
        }
}