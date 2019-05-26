package com.zzriders.cricketindoorrules.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zzriders.cricketindoorrules.R
import com.zzriders.cricketindoorrules.games.database.AbsDatabase
import com.zzriders.cricketindoorrules.games.database.model.Overs
import com.zzriders.cricketindoorrules.games.database.model.Team
import com.zzriders.cricketindoorrules.games.presenters.NewGamePresenter
import com.zzriders.cricketindoorrules.games.views.GameView
import com.zzriders.cricketindoorrules.home.BaseFragment
import kotlinx.android.synthetic.main.game_new_fragment.view.*

class NewGameFragment : BaseFragment(), GameView {

    interface NewGameListener {
        fun onNewGmeBackClicked()
        fun onPlayersClicked(teamOne: Team?, teamTwo: Team?)
        fun onRulesClicked()
        fun onOversClicked(overs: Overs?)
        fun onSaveClicked()
        fun onPlayClicked()
    }

    companion object {
        fun newGameFragment() : BaseFragment {
            return NewGameFragment()
        }
    }

    private lateinit var presenter: NewGamePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = NewGamePresenter(this,AbsDatabase.database(context!!).gameRepository())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_new_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.new_game_back.setOnClickListener{newGameListener()?.onNewGmeBackClicked()}
        view.new_game_players.setOnClickListener{newGameListener()?.onPlayersClicked(presenter.teamOne, presenter.teamTwo)}
        view.new_game_rules.setOnClickListener{newGameListener()?.onRulesClicked()}
        view.new_game_overs.setOnClickListener{newGameListener()?.onOversClicked(presenter.overs)}
        view.new_game_save.setOnClickListener{presenter.save()}
        view.new_game_play.setOnClickListener{newGameListener()?.onPlayClicked()}
    }

    fun setPlayersTeam(teamOne: Team, teamTwo: Team) {
        presenter.teamOne = teamOne
        presenter.teamTwo = teamTwo
    }

    fun setOvers(overs: Overs) {
        presenter.overs = overs
    }

    override fun onStop() {
        super.onStop()
        presenter.stopPresenting()
    }

    override fun getName() = "NewGameFragment"

    private fun newGameListener() : NewGameListener? {
        return if (activity is NewGameListener) activity as NewGameListener else null
    }
}