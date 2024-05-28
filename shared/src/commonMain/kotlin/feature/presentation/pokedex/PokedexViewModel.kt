package feature.presentation.pokedex

import core.extensions.onError
import core.extensions.onSuccess
import core.utils.BaseViewModel
import feature.domain.usecase.FetchPokemonsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class PokedexViewModel(
    private val fetchPokemonsUseCase: FetchPokemonsUseCase
) : BaseViewModel<PokemonState, PokemonEvent>() {
    override val initialState: PokemonState
        get() = PokemonState()


    init {
        fetchPokemons()
    }

    fun fetchPokemons() {
        fetchPokemonsUseCase(Unit).onStart {
            updateState { it.copy(isLoading = true) }
        }.onSuccess { pokemons ->
            updateState { it.copy(pokemons = pokemons) }
        }.onError { error ->
            updateState { it.copy(error = error) }
        }.onCompletion {
            updateState { it.copy(isLoading = false) }
        }.launchIn(scope)
    }
}