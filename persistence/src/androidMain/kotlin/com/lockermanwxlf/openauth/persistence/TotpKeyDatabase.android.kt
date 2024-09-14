package com.lockermanwxlf.openauth.persistence

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<TotpKeyDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("openauth_keys.db")
    return Room.databaseBuilder<TotpKeyDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}