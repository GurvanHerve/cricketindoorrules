package com.zzriders.cricketindoorrules.games.database.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zzriders.cricketindoorrules.games.database.model.Game
import com.zzriders.cricketindoorrules.games.database.model.Team

@Dao
interface TeamRepository {
    @Query("SELECT * FROM team WHERE uid LIKE :id")
    fun get(id: String) : Team

    @Query("SELECT game.team FROM game WHERE game.uid LIKE :game")
    fun get(game: Game) : List<Team>

    @Insert
    fun create(team: Team)

    @Update
    fun save(team: Team)
}