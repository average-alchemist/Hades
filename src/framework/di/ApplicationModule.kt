package io.aethibo.framework.di

import io.aethibo.utils.JwtService
import org.koin.dsl.module

val applicationModule = module {
    single { JwtService }
}