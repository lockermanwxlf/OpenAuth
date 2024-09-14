package com.lockermanwxlf.openauth.di

import com.lockermanwxlf.openauth.persistence.TotpKeyDatabase
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val commonModule = module {
    single { get<TotpKeyDatabase>().getTotpKeyDao() }
}

expect val platformModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(commonModule, platformModule)
    }
}