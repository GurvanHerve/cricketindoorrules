package com.zzriders.cricketindoorrules.games.database.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zzriders.cricketindoorrules.games.database.model.Game

@Dao
interface GameRepository {
    @Query("SELECT * FROM game WHERE uid LIKE :uid")
    fun get(uid: String): Game

    @Insert
    fun create(game: Game)

    @Update
    fun save(game: Game)
}