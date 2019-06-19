package com.zzriders.cricketindoorrules.games.database.repositories

import androidx.room.*
import com.zzriders.cricketindoorrules.games.database.model.Team
import io.reactivex.Single

@Dao
interface TeamRepository {
    @Query("SELECT * FROM team WHERE uid LIKE :uid")
    fun get(uid: String) : Single<Team?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(team: Team) // todo bulk create

    @Update
    fun save(team: Team) // todo bulk save
}