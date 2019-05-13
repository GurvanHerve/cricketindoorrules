package com.zzriders.cricketindoorrules.games.database.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zzriders.cricketindoorrules.games.database.model.Game
import com.zzriders.cricketindoorrules.games.database.model.Overs

@Dao
interface OversRepository {
    @Query("SELECT * FROM overs WHERE uid LIKE :id")
    fun get(id: String) : Overs

//    @Query("SELECT game.overs FROM game WHERE game.uid LIKE :game")
//    fun get(game: Game) : Overs

    @Insert
    fun create(overs: Overs)

    @Update
    fun save(overs: Overs)
}