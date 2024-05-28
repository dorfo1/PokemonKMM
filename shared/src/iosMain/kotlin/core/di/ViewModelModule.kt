package core.di

import feature.presentation.pokedex.PokedexViewModel
import org.koin.dsl.module

actual val viewModelModules = module {
    factory { PokedexViewModel(get()) }
}