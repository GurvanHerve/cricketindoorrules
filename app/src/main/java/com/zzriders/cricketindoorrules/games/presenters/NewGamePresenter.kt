package com.zzriders.cricketindoorrules.games.presenters

import android.util.Log
import com.zzriders.cricketindoorrules.games.database.model.Team
import com.zzriders.cricketindoorrules.games.database.repositories.GameRepository
import com.zzriders.cricketindoorrules.games.database.repositories.TeamRepository
import com.zzriders.cricketindoorrules.games.views.GameView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
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
    init {

        Log.d("echo", "NewGamePresenter -- teamOneUid: " + teamOneUid)
        Log.d("echo", "NewGamePresenter -- teamTwoUid: " + teamTwoUid)


        if (teamOneUid != null) {
            Single.create<Team?>{teamRepository.get(teamOneUid)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { team -> teamOne = team ?: Team() }
        }

        if (teamTwoUid != null) {
            Single.create<Team?>{teamRepository.get(teamTwoUid)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { team -> teamTwo = team ?: Team() }
        }
    }

    fun save() {

    }
}