package com.lockermanwxlf.openauth.repositories

import com.lockermanwxlf.openauth.persistence.TotpKey
import com.lockermanwxlf.openauth.persistence.TotpKeyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TotpKeyRepository(
    private val totpKeyDao: TotpKeyDao
) {
    fun getTotpKeysFlow() = totpKeyDao.getAllAsFlow()

    suspend fun addTotpKey(totpKey: TotpKey) = withContext(Dispatchers.IO) {
        totpKeyDao.upsertAll(totpKey)
    }
}