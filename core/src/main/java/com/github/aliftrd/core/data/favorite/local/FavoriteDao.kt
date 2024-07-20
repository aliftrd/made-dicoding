package com.github.aliftrd.core.data.favorite.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.aliftrd.core.data.favorite.model.FavoriteItem

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(story: FavoriteItem): Unit

    @Query("SELECT * FROM favorite ORDER BY id DESC")
    fun getAllMovie(): List<FavoriteItem>

    @Query("SELECT * FROM favorite WHERE movieId = :movieId")
    suspend fun getMovie(movieId: Int): FavoriteItem?

    @Query("DELETE FROM favorite WHERE movieId = :movieId")
    suspend fun deleteMovie(movieId: Int)

    @Query("DELETE FROM favorite")
    suspend fun deleteAll()
}