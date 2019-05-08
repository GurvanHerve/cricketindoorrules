package com.zzriders.cricketindoorrules.games.presenters

import com.zzriders.cricketindoorrules.games.views.PlayersView

class PlayersPresenter(val view: PlayersView) {

    private val team = Team()

    fun setTeamName(name: String) {
        team.name = name
    }

    fun incrementPlayersCount() {
        if (team.playerCount == 0) view.disableDecrementPlayer(false)
        team.playerCount = team.playerCount + 1
    }

    fun decrementPlayersCount() {
        team.playerCount = team.playerCount - 1

        if (team.playerCount == 0) view.disableDecrementPlayer(true)
    }
}