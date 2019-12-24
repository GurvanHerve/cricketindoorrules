package com.zzriders.cricketindoorrules.games.database.repositories

import androidx.room.Dao
import androidx.room.Query
import com.zzriders.cricketindoorrules.games.database.model.Rules
import io.reactivex.Single

@Dao
interface RulesRepository {
    @Query("SELECT * FROM rules WHERE game_uid LIKE :uid")
    fun get(uid: String) : Single<List<Rules>>
}