package com.github.aliftrd.core.data.lib

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.aliftrd.core.data.favorite.local.FavoriteDao
import com.github.aliftrd.core.data.favorite.model.FavoriteItem

@Database(
    entities = [FavoriteItem::class],
    version = 1,
    exportSchema = true,
)
abstract class MoovaiDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}