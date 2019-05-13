package com.zzriders.cricketindoorrules.database

import androidx.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameRepositoryTest : RepositoryTest<GameRepository>() {

    override fun createRepository(db: Database): GameRepository {
        return db.gameRepository()
    }

    @Test
    fun createGame() {
        val game = Game()
        game.name = "New Game"
    }
}