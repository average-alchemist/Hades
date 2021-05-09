package io.aethibo.framework.di

import io.aethibo.repositories.DefaultMainRepository
import io.aethibo.repositories.MainRepository
import org.koin.dsl.module

val repositoriesModule = module {

    // single<MainRepository> { InMemoryRepository() }
    single<MainRepository> { DefaultMainRepository() }
}