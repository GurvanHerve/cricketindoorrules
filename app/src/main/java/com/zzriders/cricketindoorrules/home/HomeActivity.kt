package com.zzriders.cricketindoorrules.home

import android.os.Bundle
import com.zzriders.cricketindoorrules.BaseActivity
import com.zzriders.cricketindoorrules.R
import com.zzriders.cricketindoorrules.games.NewGameFragment
import com.zzriders.cricketindoorrules.games.PlayersFragment

class HomeActivity : BaseActivity(), HomeFragment.HomeListener, NewGameFragment.NewGameListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        showFragment(R.id.fragment_container, HomeFragment(), false)
    }

    override fun onGameClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNewTypeGameClicked() {
        showFragment(R.id.fragment_container, NewGameFragment(), true)
    }

    override fun onNewGmeBackClicked() {
        dismissFragment()
    }

    override fun onPlayersClicked() {
        showFragment(R.id.fragment_container, PlayersFragment(), true)
    }

    override fun onRulesClicked() {
        // todo show rules fragment
    }

    override fun onOversClicked() {
        // todo show overs fragment
    }

    override fun onSaveClicked() {
        // anything to do ?
    }

    override fun onPlayClicked() {
        // todo show play fragment
    }
}