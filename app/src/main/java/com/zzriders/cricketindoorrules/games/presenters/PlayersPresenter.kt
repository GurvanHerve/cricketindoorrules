package com.zzriders.cricketindoorrules.games.presenters

import com.zzriders.cricketindoorrules.games.database.model.Team
import com.zzriders.cricketindoorrules.games.views.PlayersView

class PlayersPresenter(val view: PlayersView, val teamOne: Team = Team(), val teamTwo: Team = Team()) {

    fun startPresenting() {
        view.setPlayerCountTeamOne(teamOne.playersCount.toString())
        view.setPlayerCountTeamTwo(teamTwo.playersCount.toString())
        view.disableDecrementPlayerTeamOne(teamOne.playersCount == 0)
        view.disableDecrementPlayerTeamTwo(teamTwo.playersCount == 0)
    }

    fun incrementPlayersCountTeamOne() {
        if (teamOne.playersCount == 0) view.disableDecrementPlayerTeamOne(false)
        teamOne.playersCount = teamOne.playersCount + 1
        view.setPlayerCountTeamOne(teamOne.playersCount.toString())
        validateAndEnableOk()
    }

    fun decrementPlayersCountTeamOne() {
        teamOne.playersCount = teamOne.playersCount - 1
        view.setPlayerCountTeamOne(teamOne.playersCount.toString())
        if (teamOne.playersCount == 0) view.disableDecrementPlayerTeamOne(true)
        validateAndEnableOk()
    }

    fun incrementPlayersCountTeamTwo() {
        if (teamTwo.playersCount == 0) view.disableDecrementPlayerTeamTwo(false)
        teamTwo.playersCount = teamTwo.playersCount + 1
        view.setPlayerCountTeamTwo(teamTwo.playersCount.toString())
        validateAndEnableOk()
    }

    fun decrementPlayersCountTeamTwo() {
        teamTwo.playersCount = teamTwo.playersCount - 1
        view.setPlayerCountTeamTwo(teamTwo.playersCount.toString())
        if (teamTwo.playersCount == 0) view.disableDecrementPlayerTeamTwo(true)
        validateAndEnableOk()
    }

    private fun validateAndEnableOk() {
        view.enableOk(teamOne.playersCount > 0 && teamTwo.playersCount > 0)
    }

    fun teamOne() : Team {
        return teamOne
    }

    fun teamTwo() : Team {
        return teamTwo
    }
}