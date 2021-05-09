package io.aethibo.framework.di

import io.aethibo.repositories.utils.DatabaseFactory
import io.aethibo.utils.JwtService
import org.koin.dsl.module

val applicationModule = module {
    single { JwtService }
    single { DatabaseFactory }
}