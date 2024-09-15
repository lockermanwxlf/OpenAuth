package com.lockermanwxlf.openauth.repositories

import com.lockermanwxlf.openauth.persistence.TotpKeyDao

class TotpKeyRepository(
    private val totpKeyDao: TotpKeyDao
) {
    fun getTotpKeysFlow() = totpKeyDao.getAllAsFlow()
}