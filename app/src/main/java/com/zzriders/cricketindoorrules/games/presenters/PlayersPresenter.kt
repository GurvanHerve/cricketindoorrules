package com.zzriders.cricketindoorrules.games.presenters

import com.zzriders.cricketindoorrules.games.database.model.Team
import com.zzriders.cricketindoorrules.games.database.repositories.TeamRepository
import com.zzriders.cricketindoorrules.games.views.PlayersView
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.Single.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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
    private val compositeDisposable = CompositeDisposable()

    fun startPresenting() {
        val req: Single<Wrapper>
        req = if (teamOneUid == null || teamTwoUid == null) {
            just(Wrapper(Team(), Team()))
        } else {
            zip(
                teamRepository.get(teamOneUid),
                teamRepository.get(teamTwoUid),
                BiFunction { t1: Team?, t2: Team? ->  Wrapper(t1, t2)}
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
        compositeDisposable.add(req.subscribe{ wrapper ->
            teamOne = wrapper.teamOne
            teamTwo = wrapper.teamTwo
            view.setPlayerCountTeamOne(teamOne.playersCount.toString())
            view.setPlayerCountTeamTwo(teamTwo.playersCount.toString())
            view.disableDecrementPlayerTeamOne(teamOne.playersCount == 0)
            view.disableDecrementPlayerTeamTwo(teamTwo.playersCount == 0)
            validateAndEnableOk()
        })
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
        compositeDisposable.add(
            Completable.fromAction {
                teamRepository.create(teamOne)
                teamRepository.create(teamTwo)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { view.dismiss() }
        )
    }

    fun stopPresenting() {
        compositeDisposable.clear()
    }

    private data class Wrapper(val t1: Team?, val t2: Team?) {
        val teamOne = t1?: Team()
        val teamTwo = t2?: Team()
    }
}