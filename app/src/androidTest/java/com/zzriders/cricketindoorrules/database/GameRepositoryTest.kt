package com.zzriders.cricketindoorrules.database

import androidx.test.runner.AndroidJUnit4
import com.zzriders.cricketindoorrules.games.database.AbsDatabase
import com.zzriders.cricketindoorrules.games.database.model.Game
import com.zzriders.cricketindoorrules.games.database.repositories.GameRepository
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameRepositoryTest : RepositoryTest<GameRepository>() {

    override fun createRepository(db: AbsDatabase): GameRepository {
        return db.gameRepository()
    }

    @Test
    fun testCreateGame() {
        val game = game()

        repository.create(game)

        val cursor = db.query("SELECT * FROM game", null)

        assert(cursor.moveToFirst())
        Assert.assertEquals(1, cursor.count)

        Assert.assertEquals(game.uid, cursor.getString(cursor.getColumnIndex("uid")))
        Assert.assertEquals(game.name, cursor.getString(cursor.getColumnIndex("name")))
    }

    @Test
    fun testGetGameForId() {
        val game = game()
        repository.create(game)

        val res = repository.get(game.uid)
        Assert.assertNotNull(res)
        Assert.assertEquals(game.uid, res.uid)
        Assert.assertEquals(game.name, res.name)
    }

    @Test
    fun testSaveGame() {
        val game = game()
        repository.create(game)

        game.name = "theTeam"
        repository.save(game)

        Assert.assertEquals(1, db.query("SELECT * FROM game", null).count)

        val res = repository.get(game.uid)
        Assert.assertNotNull(res)
        Assert.assertEquals(game.uid, res.uid)
        Assert.assertEquals(game.name, res.name)
    }

    private fun game() : Game {
        val game = Game()
        game.name = "aGame"
        return game
    }
}