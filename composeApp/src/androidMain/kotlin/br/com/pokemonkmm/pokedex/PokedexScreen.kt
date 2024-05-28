package br.com.pokemonkmm.pokedex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.pokemonkmm.R
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import core.error.CustomError
import feature.domain.model.Pokemon
import feature.presentation.pokedex.PokedexViewModel
import feature.presentation.pokedex.PokemonState
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokedexScreen(viewModel: PokedexViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()

    PokedexContent(state = state, onTryAgain = viewModel::fetchPokemons)
}

@Composable
fun PokedexContent(state: PokemonState, onTryAgain: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.pokedex_title)) })
    }, content = { _ ->
        Column(modifier = Modifier.fillMaxSize()) {

            when {
                state.isLoading -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

                state.error != null -> PokedexErrorContent(
                    error = state.error,
                    onTryAgain = onTryAgain
                )

                else -> PokedexList(state.pokemons)
            }
        }
    })

}

@Composable
fun PokedexList(pokemons: List<Pokemon>) {
    LazyColumn {
        items(items = pokemons) { pokemon ->
            LaunchedEffect(Unit) {
                println("IMAGEM ${pokemon.thumbnail}}")


            }
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = pokemon.thumbnail,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${pokemon.number} - ${pokemon.name}")
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
private fun PokedexErrorContent(error: CustomError?, onTryAgain: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = stringResource(id = R.string.error_message_title), fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.error_message_message), fontSize = 14.sp)
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp), onClick = onTryAgain
        ) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}

@Preview
@Composable
private fun PokedexContentPreview() {
    PokedexContent(
        state = PokemonState(
            pokemons = listOf(
                Pokemon(
                    1,
                    "Bulbasaur",
                    "001",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                )
            )
        ), onTryAgain = {})
}