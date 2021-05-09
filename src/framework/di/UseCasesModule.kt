package io.aethibo.framework.di

import io.aethibo.usecases.*
import org.koin.dsl.module

val useCasesModule = module(createdAtStart = true) {

    /**
     * Thoughts use cases
     */
    single<GetThoughtsUseCase> { GetThoughtsUseCaseImpl(get()) }
    single<GetThoughtUseCase> { GetThoughtUseCaseImpl(get()) }
    single<AddThoughtUseCase> { AddThoughtUseCaseImpl(get()) }
    single<UpdateThoughtUseCase> { UpdateThoughtUseCaseImpl(get()) }
    single<RemoveThoughtUseCase> { RemoveThoughtUseCaseImpl(get()) }

    /**
     * User use cases
     */
    single<SignUpUserUseCase> { SignUpUserUseCaseImpl(get()) }
}