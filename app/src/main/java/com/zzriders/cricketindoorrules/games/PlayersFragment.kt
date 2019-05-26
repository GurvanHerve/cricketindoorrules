package com.zzriders.cricketindoorrules.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.zzriders.cricketindoorrules.R
import com.zzriders.cricketindoorrules.games.database.AbsDatabase.Companion.database
import com.zzriders.cricketindoorrules.games.database.model.Team
import com.zzriders.cricketindoorrules.games.presenters.PlayersPresenter
import com.zzriders.cricketindoorrules.games.views.CounterView
import com.zzriders.cricketindoorrules.games.views.CounterView.CounterListener
import com.zzriders.cricketindoorrules.games.views.PlayersView
import com.zzriders.cricketindoorrules.home.BaseFragment
import kotlinx.android.synthetic.main.game_players_fragment.view.*

class PlayersFragment : BaseFragment(), PlayersView {

    interface PlayersListener {
        fun onPlayersDismissClicked()
        fun onPlayersConfirmed(teamOne: Team, teamTwo: Team)
    }

    companion object {
        private val kTEAM_ONE_UID = "teamOneUid"
        private val kTEAM_TWO_UID = "teamTwoUid"

        fun playersFragment() : BaseFragment {
            return playersFragment(null, null)
        }

        fun playersFragment(teamOne: Team?, teamTwo: Team?) : BaseFragment {
            val args = Bundle()
            if (teamOne != null) args.putString(kTEAM_ONE_UID, teamOne.uid)
            if (teamTwo != null) args.putString(kTEAM_TWO_UID, teamTwo.uid)

            val fragment = PlayersFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var presenter: PlayersPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = PlayersPresenter(this,
            database(context!!).teamRepository(),
            arguments?.getString("teamOneUid"),
            arguments?.getString("teamTwoUid"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_players_fragment, container, false)
    }

    private lateinit var okView: AppCompatTextView
    private lateinit var teamOneView: CounterView
    private lateinit var teamTwoView: CounterView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.players_back.setOnClickListener{playersListener()?.onPlayersDismissClicked()}
        okView = view.players_ok
        okView.setOnClickListener{
            presenter.saveTeams()
        }
        teamOneView = view.players_team_one
        teamOneView.counterListener = counterTeamOneListener(presenter)
        teamTwoView = view.players_team_two
        teamTwoView.counterListener = counterTeamTwoListener(presenter)

        presenter.startPresenting()
    }

    override fun disableDecrementPlayerTeamOne(disable: Boolean) {
        teamOneView.disableDecrement(disable)
    }

    override fun disableDecrementPlayerTeamTwo(disable: Boolean) {
        teamTwoView.disableDecrement(disable)
    }

    override fun setPlayerCountTeamOne(value: String) {
        teamOneView.setIndicatorValue(value)
    }

    override fun setPlayerCountTeamTwo(value: String) {
        teamTwoView.setIndicatorValue(value)
    }

    override fun enableOk(enable: Boolean) {
        okView.isEnabled = enable
    }

    override fun dismiss() {
        playersListener()?.onPlayersConfirmed(presenter.teamOne, presenter.teamTwo)
    }

    override fun onStop() {
        super.onStop()
        presenter.stopPresenting()
    }

    private fun counterTeamOneListener(presenter: PlayersPresenter) : CounterListener {
        return object : CounterListener {
            override fun onIncrementClicked() {
                presenter.incrementPlayersCountTeamOne()
            }

            override fun onDecrementClicked() {
                presenter.decrementPlayersCountTeamOne()
            }
        }
    }

    private fun counterTeamTwoListener(presenter: PlayersPresenter) : CounterListener {
        return object : CounterListener {
            override fun onIncrementClicked() {
                presenter.incrementPlayersCountTeamTwo()
            }

            override fun onDecrementClicked() {
                presenter.decrementPlayersCountTeamTwo()
            }
        }
    }

    private fun playersListener() : PlayersListener? {
        return if (activity is PlayersListener) activity as PlayersListener else null
    }
}