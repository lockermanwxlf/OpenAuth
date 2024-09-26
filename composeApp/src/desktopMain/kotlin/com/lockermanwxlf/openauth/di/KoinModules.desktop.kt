package com.lockermanwxlf.openauth.di

import com.lockermanwxlf.openauth.persistence.TotpKeyDatabase
import com.lockermanwxlf.openauth.persistence.getDatabaseBuilder
import com.lockermanwxlf.openauth.util.DesktopInputStreamProvider
import com.lockermanwxlf.openauth.util.InputStreamProvider
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<TotpKeyDatabase> { TotpKeyDatabase.getRoomDatabase(getDatabaseBuilder()) }
    single<InputStreamProvider> { DesktopInputStreamProvider() }
}