package com.zzriders.cricketindoorrules.games.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zzriders.cricketindoorrules.games.database.model.Game
import com.zzriders.cricketindoorrules.games.database.model.Overs
import com.zzriders.cricketindoorrules.games.database.model.Rules
import com.zzriders.cricketindoorrules.games.database.model.Team
import com.zzriders.cricketindoorrules.games.database.repositories.GameRepository
import com.zzriders.cricketindoorrules.games.database.repositories.OversRepository
import com.zzriders.cricketindoorrules.games.database.repositories.RulesRepository
import com.zzriders.cricketindoorrules.games.database.repositories.TeamRepository

@Database(entities = [Game::class, Team::class, Rules::class, Overs::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun gameRepository(): GameRepository
    abstract fun teamRepository(): TeamRepository
    abstract fun rulesRepository(): RulesRepository
    abstract fun oversRepository(): OversRepository
}