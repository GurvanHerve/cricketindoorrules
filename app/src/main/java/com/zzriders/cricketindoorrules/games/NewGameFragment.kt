package com.zzriders.cricketindoorrules.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zzriders.cricketindoorrules.R
import com.zzriders.cricketindoorrules.games.database.AbsDatabase
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
        fun onOversClicked()
        fun onSaveClicked()
        fun onPlayClicked()
    }

    companion object {
        private val kTEAM_ONE_UID = "teamOneUid"
        private val kTEAM_TWO_UID = "teamTwoUid"

        fun newGameFragment() : BaseFragment {
            return newGameFragment(null, null)
        }

        fun newGameFragment(teamOne: Team?, teamTwo: Team?) : BaseFragment {
            val args = Bundle()
            if (teamOne != null) args.putString(kTEAM_ONE_UID, teamOne.uid)
            if (teamTwo != null) args.putString(kTEAM_TWO_UID, teamTwo.uid)

            val fragment = NewGameFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var presenter: NewGamePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = NewGamePresenter(this,
            AbsDatabase.database(context!!).gameRepository(),
            AbsDatabase.database(context!!).teamRepository(),
            arguments?.getString(kTEAM_ONE_UID),
            arguments?.getString(kTEAM_TWO_UID))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_new_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.new_game_back.setOnClickListener{newGameListener()?.onNewGmeBackClicked()}
        view.new_game_players.setOnClickListener{newGameListener()?.onPlayersClicked(presenter.teamOne, presenter.teamTwo)}
        view.new_game_rules.setOnClickListener{newGameListener()?.onRulesClicked()}
        view.new_game_overs.setOnClickListener{newGameListener()?.onOversClicked()}
        view.new_game_save.setOnClickListener{presenter.save()}
        view.new_game_play.setOnClickListener{newGameListener()?.onPlayClicked()}
    }

    override fun onStop() {
        super.onStop()
        presenter.stopPresenting()
    }

    private fun newGameListener() : NewGameListener? {
        return if (activity is NewGameListener) activity as NewGameListener else null
    }
}