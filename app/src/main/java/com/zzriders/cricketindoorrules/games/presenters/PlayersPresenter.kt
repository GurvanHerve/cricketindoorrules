package com.zzriders.cricketindoorrules.games.presenters

import android.util.Log
import com.zzriders.cricketindoorrules.games.database.model.Team
import com.zzriders.cricketindoorrules.games.database.repositories.TeamRepository
import com.zzriders.cricketindoorrules.games.views.PlayersView
import io.reactivex.Single
import io.reactivex.Single.just
import io.reactivex.Single.zip
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class PlayersPresenter(
    private val view: PlayersView,
    private val teamRepository: TeamRepository,
    private val teamOneUid: String?,
    private val teamTwoUid: String?) {

    lateinit var teamOne: Team
        private set
    lateinit var teamTwo: Team
        private set

    fun startPresenting() {
        Log.d("echo", "PlayerPresenter -- teamOneUid: " + teamOneUid)
        Log.d("echo", "PlayerPresenter -- teamTwoUid: " + teamTwoUid)
        if (teamOneUid == null || teamTwoUid == null) {
            teamOne = Team()
            teamTwo = Team()

            view.setPlayerCountTeamOne(teamOne.playersCount.toString())
            view.setPlayerCountTeamTwo(teamTwo.playersCount.toString())
            view.disableDecrementPlayerTeamOne(teamOne.playersCount == 0)
            view.disableDecrementPlayerTeamTwo(teamTwo.playersCount == 0)
            validateAndEnableOk()
        } else {
            zip(
                team(teamOneUid),
                team(teamTwoUid),
                BiFunction { t1: Team?, t2: Team? ->  Wrapper(t1, t2)}
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ wrapper ->
                    run {
                        teamOne = wrapper.teamOne
                        teamTwo = wrapper.teamTwo
                        view.setPlayerCountTeamOne(teamOne.playersCount.toString())
                        view.setPlayerCountTeamTwo(teamTwo.playersCount.toString())
                        view.disableDecrementPlayerTeamOne(teamOne.playersCount == 0)
                        view.disableDecrementPlayerTeamTwo(teamTwo.playersCount == 0)
                        validateAndEnableOk()
                    }
                }
        }
    }

    private fun team(teamUid: String) : Single<Team?> {
        return Single.create {teamRepository.get(teamUid)}
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
        Single.create<Any>{teamRepository.create(teamOne)}
            .zipWith(Single.create<Any>{teamRepository.create(teamTwo)}, BiFunction {
                return null
        })
            .su


        just({


            Log.d("echo", "PlayersPresenter.count: " + teamRepository.count())
        })
            .subscribeOn(Schedulers.io())
            .subscribe{_it -> view.dismiss()}
    }

    private class Wrapper(t1: Team?, t2: Team?) {
        val teamOne = t1?: Team()
        val teamTwo = t2?: Team()
    }
}