package com.zzriders.cricketindoorrules.database

class RulesRepositoryTest : RepositoryTest<RulesRepository>() {
    override fun createRepository(db: Database): RulesRepository {
        return db.rulesRepository()
    }
}