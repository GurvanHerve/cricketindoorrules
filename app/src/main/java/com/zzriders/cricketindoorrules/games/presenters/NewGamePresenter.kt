package com.zzriders.cricketindoorrules.games.presenters

import com.zzriders.cricketindoorrules.games.database.model.Overs
import com.zzriders.cricketindoorrules.games.database.model.Team
import com.zzriders.cricketindoorrules.games.database.repositories.GameRepository
import com.zzriders.cricketindoorrules.games.views.GameView
import io.reactivex.disposables.Disposable

class NewGamePresenter (
    private val view: GameView,
    private val gameRepository: GameRepository) {

    var teamOne: Team? = null
    var teamTwo: Team? = null
    var overs: Overs? = null
    private lateinit var disposable: Disposable

    fun save() {

    }

    fun stopPresenting() {
        disposable?.dispose()
    }
}