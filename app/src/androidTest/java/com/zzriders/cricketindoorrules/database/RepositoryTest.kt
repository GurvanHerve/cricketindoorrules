package com.zzriders.cricketindoorrules.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import java.io.IOException

abstract class RepositoryTest<Repository: Any> {
    protected lateinit var repository: Repository
    protected lateinit var db: Database

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, Database::class.java).build()
        repository = createRepository(db)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        // clean database
        db.close()
    }

    abstract fun createRepository(db: Database) : Repository
}