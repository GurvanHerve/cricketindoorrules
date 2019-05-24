package com.zzriders.cricketindoorrules.games.database.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zzriders.cricketindoorrules.games.database.model.Team

@Dao
interface TeamRepository {
    @Query("SELECT * FROM team WHERE uid LIKE :uid")
    fun get(uid: String) : Team?

    @Insert
    fun create(team: Team) // todo bulk create

    @Update
    fun save(team: Team) // todo bulk save

    @Query("SELECT COUNT (*) FROM team")
    fun count() : Int
}