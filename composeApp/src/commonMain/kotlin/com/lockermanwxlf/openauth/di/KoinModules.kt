package com.lockermanwxlf.openauth.di

import com.lockermanwxlf.openauth.persistence.TotpKeyDatabase
import com.lockermanwxlf.openauth.repositories.TotpKeyRepository
import com.lockermanwxlf.openauth.viewmodels.AddTotpKeyViewModel
import com.lockermanwxlf.openauth.viewmodels.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val commonModule = module {
    single { get<TotpKeyDatabase>().getTotpKeyDao() }
    singleOf(::TotpKeyRepository)
    viewModelOf(::HomeViewModel)
    viewModelOf(::AddTotpKeyViewModel)
}

expect val platformModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(commonModule, platformModule)
    }
}