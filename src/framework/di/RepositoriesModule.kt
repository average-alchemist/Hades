package io.aethibo.framework.di

import io.aethibo.repositories.utils.DatabaseFactory
import io.aethibo.repositories.DefaultMainRepository
import io.aethibo.repositories.MainRepository
import org.koin.dsl.module

val repositoriesModule = module {

    single { DatabaseFactory }
    // single<MainRepository> { InMemoryRepository() }
    single<MainRepository> { DefaultMainRepository() }
}