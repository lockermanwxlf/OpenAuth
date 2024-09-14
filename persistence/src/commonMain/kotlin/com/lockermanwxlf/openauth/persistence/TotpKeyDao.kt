package com.lockermanwxlf.openauth.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TotpKeyDao {
    @Upsert
    suspend fun upsertAll(vararg TotpKeys: TotpKey)

    @Delete
    suspend fun geleteAll(vararg TotpKeys: TotpKey)

    @Query("SELECT * FROM totpKeys")
    suspend fun getAll(): List<TotpKey>

    @Query("SELECT * FROM totpKeys")
    fun getAllAsFlow(): Flow<List<TotpKey>>
}