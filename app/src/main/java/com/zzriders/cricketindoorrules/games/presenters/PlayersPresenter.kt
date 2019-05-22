package com.zzriders.cricketindoorrules.games.presenters

import com.zzriders.cricketindoorrules.games.database.model.Team
import com.zzriders.cricketindoorrules.games.database.repositories.TeamRepository
import com.zzriders.cricketindoorrules.games.views.PlayersView
import io.reactivex.Single.just
import io.reactivex.schedulers.Schedulers

class PlayersPresenter(
    private val view: PlayersView,
    private val teamRepository: TeamRepository,
    teamOneUid: String?,
    teamTwoUid: String?) {

    private val teamOne: Team = if (teamOneUid == null) Team() else teamRepository.get(teamOneUid)
    private val teamTwo: Team = if (teamTwoUid == null) Team() else teamRepository.get(teamTwoUid)

    fun startPresenting() {
        view.setPlayerCountTeamOne(teamOne.playersCount.toString())
        view.setPlayerCountTeamTwo(teamTwo.playersCount.toString())
        view.disableDecrementPlayerTeamOne(teamOne.playersCount == 0)
        view.disableDecrementPlayerTeamTwo(teamTwo.playersCount == 0)
        validateAndEnableOk()
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

    fun saveTeams() {
        just({
            teamRepository.create(teamOne)
            teamRepository.create(teamTwo)
        })
            .subscribeOn(Schedulers.io())
            .subscribe{it -> view.dismiss()}
    }

    fun teamOne() : Team {
        return teamOne
    }

    fun teamTwo() : Team {
        return teamTwo
    }
}