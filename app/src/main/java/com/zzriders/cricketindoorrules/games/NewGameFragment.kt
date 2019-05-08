package com.zzriders.cricketindoorrules.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zzriders.cricketindoorrules.home.BaseFragment

class NewGameFragment : BaseFragment() {

    interface NewGameListener {
        fun onNewGmeBackClicked()
        fun onPlayersClicked()
        fun onRulesClicked()
        fun onOversClicked()
        fun onSaveClicked()
        fun onPlayClicked()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_new_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.new_game_back.setOnClickListener{newGameListener()?.onNewGmeBackClicked()}
        view.new_game_players.setOnClickListener{newGameListener()?.onPlayersClicked()}
        view.new_game_rules.setOnClickListener{newGameListener()?.onRulesClicked()}
        view.new_game_overs.setOnClickListener{newGameListener()?.onRulesClicked()}
        view.new_game_save.setOnClickListener{newGameListener()?.onRulesClicked()}
        view.new_game_play.setOnClickListener{newGameListener()?.onRulesClicked()}
    }

    private fun newGameListener() : NewGameFragment.NewGameListener? {
        return if (activity is NewGameListener) activity as NewGameListener else null
    }
}