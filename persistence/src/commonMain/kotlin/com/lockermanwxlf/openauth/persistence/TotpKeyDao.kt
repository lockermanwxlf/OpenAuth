package com.lockermanwxlf.openauth.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TotpKeyDao {
    @Upsert
    suspend fun UpsertAll(vararg TotpKeys: TotpKey)

    @Delete
    suspend fun DeleteAll(vararg TotpKeys: TotpKey)

    @Query("SELECT * FROM totpKeys")
    suspend fun GetAll(): List<TotpKey>

    @Query("SELECT * FROM totpKeys")
    fun GetAllAsFlow(): Flow<List<TotpKey>>
}