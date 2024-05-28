package core.di

import feature.presentation.pokedex.PokedexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual val viewModelModules = module {
    viewModel { PokedexViewModel(get()) }
}