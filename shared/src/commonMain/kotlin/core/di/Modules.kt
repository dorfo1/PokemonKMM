package core.di

import core.network.HttpClientFactory
import feature.data.remote.PokemonRemoteDataSource
import feature.data.repository.PokemonRepositoryImpl
import feature.domain.repository.PokemonRepository
import feature.domain.usecase.FetchPokemonsUseCase
import io.ktor.client.HttpClient
import org.koin.dsl.module

internal val networkModule = module {
    single<HttpClient> {
        HttpClientFactory.create()
    }
}

internal val dataSourceModule = module {
    single { PokemonRemoteDataSource(get()) }
}

internal val useCasesModules = module {
    single { FetchPokemonsUseCase(get()) }
}

internal val repositoryModules = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}

fun sharedModules() = listOf(
    repositoryModules,
    useCasesModules,
    viewModelModules,
    networkModule,
    dataSourceModule
)
