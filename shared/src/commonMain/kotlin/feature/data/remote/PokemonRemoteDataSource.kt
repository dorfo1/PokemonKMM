package feature.data.remote

import feature.data.dto.PokemonDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class PokemonRemoteDataSource(
    private val httpClient: HttpClient
) {

    companion object {
        const val URL =
            "https://pokeapi.co/api/v2/pokemon"
    }

    suspend fun fetchPokemons(): List<PokemonDTO> {
        val numbers = (1..150).toList()



        return coroutineScope {
            val pokemons = numbers.map { number ->
                async(Dispatchers.IO) {
                    val pokemon: PokemonDTO = httpClient.get("$URL/$number").body()
                    pokemon
                }
            }.awaitAll()
            pokemons.sortedBy { it.id }
        }

    }
}