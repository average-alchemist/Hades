package io.aethibo.framework.di

import io.aethibo.usecases.*
import org.koin.dsl.module

val useCasesModule = module(createdAtStart = true) {

    single<GetThoughtsUseCase> { GetThoughtsUseCaseImpl(get()) }
    single<GetThoughtUseCase> { GetThoughtUseCaseImpl(get()) }
    single<AddThoughtUseCase> { AddThoughtUseCaseImpl(get()) }
    single<UpdateThoughtUseCase> { UpdateThoughtUseCaseImpl(get()) }
    single<RemoveThoughtUseCase> { RemoveThoughtUseCaseImpl(get()) }
}