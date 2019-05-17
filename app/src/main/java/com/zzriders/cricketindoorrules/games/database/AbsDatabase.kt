package com.zzriders.cricketindoorrules.games.database

import android.content.Context
import androidx.room.*
import androidx.room.Database
import com.zzriders.cricketindoorrules.games.database.model.Game
import com.zzriders.cricketindoorrules.games.database.model.Overs
import com.zzriders.cricketindoorrules.games.database.model.Rules
import com.zzriders.cricketindoorrules.games.database.model.Team
import com.zzriders.cricketindoorrules.games.database.repositories.GameRepository
import com.zzriders.cricketindoorrules.games.database.repositories.OversRepository
import com.zzriders.cricketindoorrules.games.database.repositories.RulesRepository
import com.zzriders.cricketindoorrules.games.database.repositories.TeamRepository

@Database(entities = [Game::class, Team::class, Rules::class, Overs::class], version = 1, exportSchema = false)
abstract class AbsDatabase : RoomDatabase() {
    abstract fun gameRepository(): GameRepository
    abstract fun teamRepository(): TeamRepository
    abstract fun rulesRepository(): RulesRepository
    abstract fun oversRepository(): OversRepository

    companion object {
        @Volatile private var INSTANCE: AbsDatabase? = null

        fun database(context: Context): AbsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AbsDatabase::class.java, "app.db").build()
    }
}