package com.zzriders.cricketindoorrules.games.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zzriders.cricketindoorrules.games.model.Game

@Dao
interface GameRepository {
    @Query("SELECT * FROM game WHERE uid LIKE :id")
    fun getForId(id: String): Game

    @Insert
    fun save(game: Game)
}