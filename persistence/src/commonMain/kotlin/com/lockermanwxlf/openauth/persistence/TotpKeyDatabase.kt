package com.lockermanwxlf.openauth.persistence

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

@Database(
    entities = [TotpKey::class],
    version = 1,
    exportSchema = true
)
@ConstructedBy(TotpKeyDatabaseConstructor::class)
abstract class TotpKeyDatabase: RoomDatabase() {
    abstract fun getTotpKeyDao(): TotpKeyDao

    companion object {
        fun getRoomDatabase(builder: RoomDatabase.Builder<TotpKeyDatabase>): TotpKeyDatabase {
            return builder
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
    }
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object TotpKeyDatabaseConstructor : RoomDatabaseConstructor<TotpKeyDatabase> {
    override fun initialize(): TotpKeyDatabase
}