package com.zzriders.cricketindoorrules.home

import android.os.Bundle
import com.zzriders.cricketindoorrules.BaseActivity
import com.zzriders.cricketindoorrules.R
import com.zzriders.cricketindoorrules.games.NewGameFragment
import com.zzriders.cricketindoorrules.games.NewGameFragment.Companion.newGameFragment
import com.zzriders.cricketindoorrules.games.PlayersFragment
import com.zzriders.cricketindoorrules.games.PlayersFragment.Companion.playersFragment
import com.zzriders.cricketindoorrules.games.database.model.Team

class HomeActivity : BaseActivity(), HomeFragment.HomeListener, NewGameFragment.NewGameListener, PlayersFragment.PlayersListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        replaceFragment(R.id.fragment_container, HomeFragment(), false)
    }

    override fun onGameClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNewTypeGameClicked() {
        showFragment(R.id.fragment_container, newGameFragment(), true)
    }

    override fun onNewGmeBackClicked() {
        dismissFragment()
    }

    override fun onPlayersClicked(teamOne: Team?, teamTwo: Team?) {
        showFragment(R.id.fragment_container, playersFragment(teamOne, teamTwo), true)
    }

    override fun onPlayersDismissClicked() {
        dismissFragment()
    }

    override fun onPlayersConfirmed(teamOne: Team, teamTwo: Team) {
        val fragment = supportFragmentManager.findFragmentByTag("NewGameFragment") // todo generify method
        val newGameFragment = if (fragment is NewGameFragment) fragment else null
        newGameFragment?.setPlayersTeam(teamOne, teamTwo)
        dismissDisplayedFragment()
    }

    override fun onRulesClicked() {
        // todo show rules fragment
    }

    override fun onOversClicked(overs: Overs?) {
        addFragment(R.id.fragment_container, oversFragment(overs), true)
    }

    override fun onOversDismissClicked() {
        dismissDisplayedFragment()
    }

    override fun onOversConfirmed(overs: Overs) {
        val fragment = supportFragmentManager.findFragmentByTag("NewGameFragment") // todo generify method
        val newGameFragment = if (fragment is NewGameFragment) fragment else null
        newGameFragment?.setOvers(overs)
        dismissDisplayedFragment()
    }

    override fun onSaveClicked() {
        // anything to do ?
    }

    override fun onPlayClicked() {
        // todo show play fragment
    }
}