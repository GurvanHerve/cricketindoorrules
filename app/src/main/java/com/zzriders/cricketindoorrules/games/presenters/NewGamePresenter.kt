package com.zzriders.cricketindoorrules.games.presenters

import com.zzriders.cricketindoorrules.games.database.model.Team
import com.zzriders.cricketindoorrules.games.database.repositories.GameRepository
import com.zzriders.cricketindoorrules.games.database.repositories.TeamRepository
import com.zzriders.cricketindoorrules.games.views.GameView
import io.reactivex.Single.zip
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class NewGamePresenter(
    private val view: GameView,
    private val gameRepository: GameRepository,
    teamRepository: TeamRepository,
    teamOneUid: String?,
    teamTwoUid: String?) {

    var teamOne: Team? = null
        private set
    var teamTwo: Team? = null
        private set
    private lateinit var disposable: Disposable
    init {

        if (teamOneUid != null && teamTwoUid != null) {
            disposable = zip(
                teamRepository.get(teamOneUid),
                teamRepository.get(teamTwoUid),
                BiFunction { t1: Team?, t2: Team? -> Wrapper(t1!!, t2!!) }
            )
                .subscribeOn(Schedulers.io())
                .subscribe { wrapper ->
                    teamOne = wrapper.teamOne
                    teamTwo = wrapper.teamTwo
                }
        }
    }

    fun save() {

    }
}