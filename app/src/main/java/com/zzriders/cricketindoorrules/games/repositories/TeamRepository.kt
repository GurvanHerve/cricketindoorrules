package com.zzriders.cricketindoorrules.games.repositories

import com.zzriders.cricketindoorrules.games.model.Game
import com.zzriders.cricketindoorrules.games.model.Team

interface TeamRepository {
    fun getForId(id: String) : Team
    fun getTeamsForGame(game: Game) : List<Team>
    fun save(team: Team)
}