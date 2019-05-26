package com.zzriders.cricketindoorrules.games.presenters

import com.zzriders.cricketindoorrules.games.database.model.Overs
import com.zzriders.cricketindoorrules.games.database.repositories.OversRepository
import com.zzriders.cricketindoorrules.games.views.OversView
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.Single.just
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OversPresenter(
    private val view: OversView,
    private val oversRepository: OversRepository,
    private val oversUid: String?) {

    lateinit var overs: Overs
        private set
    private val compositeDisposable = CompositeDisposable()

    fun startPresenting() {
        val req: Single<Overs>
        req = if (oversUid == null) {
            just(Overs())
        } else {
            oversRepository.get(oversUid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
        compositeDisposable.add(req.subscribe{ overs ->
            this.overs = overs
            view.setOversCount(overs.oversCount.toString())
            view.setBowlsCount(overs.bowlsCount.toString())
            view.disableDecrementOversCount(overs.oversCount == 0)
            view.disableDecrementBowlsCount(overs.bowlsCount == 0)
            validateAndEnableOk()
        })
    }

    fun incrementOversCount() {
        if (overs.oversCount == 0) view.disableDecrementOversCount(false)
        overs.oversCount += 1
        view.setOversCount(overs.oversCount.toString())
        validateAndEnableOk()
    }

    fun decrementOversCount() {
        overs.oversCount -= 1
        view.setOversCount(overs.oversCount.toString())
        if (overs.oversCount== 0) view.disableDecrementOversCount(true)
        validateAndEnableOk()
    }

    fun incrementBowsCount() {
        if (overs.bowlsCount == 0) view.disableDecrementBowlsCount(false)
        overs.bowlsCount += 1
        view.setBowlsCount(overs.bowlsCount.toString())
        validateAndEnableOk()
    }

    fun decrementBowlsCount() {
        overs.bowlsCount -= 1
        view.setBowlsCount(overs.bowlsCount.toString())
        if (overs.bowlsCount== 0) view.disableDecrementBowlsCount(true)
        validateAndEnableOk()
    }

    private fun validateAndEnableOk() {
        view.enableOk(overs.oversCount > 0 && overs.bowlsCount> 0)
    }

    fun saveOvers() {
        compositeDisposable.add(
            Completable.fromAction {
                oversRepository.create(overs)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{  view.dismiss() }
        )
    }

    fun stopPresenting() {
        compositeDisposable.clear()
    }
}