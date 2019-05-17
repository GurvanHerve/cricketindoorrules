package com.zzriders.cricketindoorrules.database

import androidx.test.runner.AndroidJUnit4
import com.zzriders.cricketindoorrules.games.database.AbsDatabase
import com.zzriders.cricketindoorrules.games.database.model.Overs
import com.zzriders.cricketindoorrules.games.database.repositories.OversRepository
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OversRepositoryTest : RepositoryTest<OversRepository>() {
    override fun createRepository(db: AbsDatabase): OversRepository {
        return db.oversRepository()
    }

    @Test
    fun testCreateOvers() {
        val overs = overs()

        repository.create(overs)

        val cursor = db.query("SELECT * FROM overs", null)

        assert(cursor.moveToFirst())
        Assert.assertEquals(1, cursor.count)

        Assert.assertEquals(overs.uid, cursor.getString(cursor.getColumnIndex("uid")))
        Assert.assertEquals(overs.oversCount, cursor.getString(cursor.getColumnIndex("overs_count")))
        Assert.assertEquals(overs.bowlsCount, cursor.getInt(cursor.getColumnIndex("bowls_count")))
    }

    @Test
    fun testGetOversForId() {
        val overs = overs()
        repository.create(overs)

        val res = repository.get(overs.uid)
        Assert.assertNotNull(res)
        Assert.assertEquals(overs.uid, res.uid)
        Assert.assertEquals(overs.oversCount, res.oversCount)
        Assert.assertEquals(overs.bowlsCount, res.bowlsCount)
    }

    @Test
    fun testGetOversForGame() {

    }

    @Test
    fun testSaveOvers() {
        val overs = overs()
        repository.create(overs)

        overs.oversCount = 3
        overs.bowlsCount = 10
        repository.save(overs)

        Assert.assertEquals(1, db.query("SELECT * FROM overs", null).count)

        val res = repository.get(overs.uid)
        Assert.assertNotNull(res)
        Assert.assertEquals(overs.uid, res.uid)
        Assert.assertEquals(overs.oversCount, res.oversCount)
        Assert.assertEquals(overs.bowlsCount, res.bowlsCount)
    }

    private fun overs() : Overs {
        val pvers = Overs()
        pvers.oversCount = 7
        pvers.bowlsCount = 4
        return pvers
    }
}