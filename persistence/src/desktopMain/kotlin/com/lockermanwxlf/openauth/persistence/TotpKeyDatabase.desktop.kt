package com.lockermanwxlf.openauth.persistence

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<TotpKeyDatabase> {
    val dbFile = File(System.getProperty("user.home"), "openauth_keys.db")
    return Room.databaseBuilder<TotpKeyDatabase>(
        name = dbFile.absolutePath,
    )
}