package com.zzriders.cricketindoorrules.database

import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TeamRespositoryTest : RepositoryTest<TeamRepository>() {
    override fun createRepository(db: Database): TeamRepository {
        return db.teamRepository()
    }

    @Test
    fun testCreateTeam() {
        val team = team()

        repository.create(team)

        val cursor = db.query("SELECT * FROM team", null)

        assert(cursor.moveToFirst())
        assertEquals(1, cursor.count)

        assertEquals(team.uid, cursor.getString(cursor.getColumnIndex("uid")))
        assertEquals(team.name, cursor.getString(cursor.getColumnIndex("name")))
        assertEquals(team.playersCount, cursor.getString(cursor.getColumnIndex("players_count")))
    }

    @Test
    fun testGetTeamForId() {
        val team = team()
        repository.create(team)

        val res = repository.get(team.uid.toString())
        assertNotNull(res)
        assertEquals(team.uid, res.uid)
        assertEquals(team.name, res.name)
        assertEquals(team.playersCount, res.playersCount)
    }

    @Test
    fun testGetTeamForGame() {

    }

    @Test
    fun testSaveTeam() {
        val team = team()
        repository.create(team)

        team.name = "theTeam"
        team.playersCount = 6
        repository.save(team)

        assertEquals(1, db.query("SELECT * FROM team", null))

        val res = repository.get(team.uid.toString())
        assertNotNull(res)
        assertEquals(team.uid, res.uid)
        assertEquals(team.name, res.name)
        assertEquals(team.playersCount, res.playersCount)
    }

    private fun team() : Team {
        val team = Team()
        team.name = "aTeam"
        team.playersCount = 11
        return team
    }
}