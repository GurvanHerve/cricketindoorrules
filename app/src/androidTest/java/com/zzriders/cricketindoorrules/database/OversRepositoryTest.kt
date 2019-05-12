package com.zzriders.cricketindoorrules.database

import androidx.test.runner.AndroidJUnit4
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OversRepositoryTest : RepositoryTest<OversRepository>() {
    override fun createRepository(db: Database): OversRepository {
        return db.oversRepository()
    }
}