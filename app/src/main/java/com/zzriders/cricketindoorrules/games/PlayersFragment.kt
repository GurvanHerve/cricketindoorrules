package com.zzriders.cricketindoorrules.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zzriders.cricketindoorrules.games.views.PlayersView
import com.zzriders.cricketindoorrules.home.BaseFragment

class PlayersFragment : BaseFragment(), PlayersView {

    interface PlayersListener {
        fun onPlayersDismissClicked()
        fun onPlayersConfirmed()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_players_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun disableDecrementPlayer(disable: Boolean) {
        // todo disable view decrement click
    }

    private fun playersListener() : PlayersListener? {
        return if (activity is PlayersListener) activity as PlayersListener else null;
    }
}