package com.zzriders.cricketindoorrules.database

import com.zzriders.cricketindoorrules.games.database.AbsDatabase
import com.zzriders.cricketindoorrules.games.database.repositories.RulesRepository

class RulesRepositoryTest : RepositoryTest<RulesRepository>() {
    override fun createRepository(db: AbsDatabase): RulesRepository {
        return db.rulesRepository()
    }
}