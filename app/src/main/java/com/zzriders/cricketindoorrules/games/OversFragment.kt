package com.zzriders.cricketindoorrules.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.zzriders.cricketindoorrules.R
import com.zzriders.cricketindoorrules.games.database.AbsDatabase.Companion.database
import com.zzriders.cricketindoorrules.games.database.model.Overs
import com.zzriders.cricketindoorrules.games.presenters.OversPresenter
import com.zzriders.cricketindoorrules.games.views.CounterView
import com.zzriders.cricketindoorrules.games.views.CounterView.CounterListener
import com.zzriders.cricketindoorrules.games.views.OversView
import com.zzriders.cricketindoorrules.home.BaseFragment
import kotlinx.android.synthetic.main.game_overs_fragment.view.*

class OversFragment : BaseFragment(), OversView {

    interface OversListener {
        fun onOversDismissClicked()
        fun onOversConfirmed(overs: Overs)
    }

    companion object {
        private val kOVERS_UID = "oversUid"

        fun oversFragment() : BaseFragment {
            return oversFragment(null)
        }

        fun oversFragment(overs: Overs?) : BaseFragment {
            val args = Bundle()
            if (overs!= null) args.putString(kOVERS_UID, overs.uid)

            val fragment = OversFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var presenter: OversPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = OversPresenter(this,
            database(context!!).oversRepository(),
            arguments?.getString(kOVERS_UID))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_overs_fragment, container, false)
    }

    private lateinit var okView: AppCompatTextView
    private lateinit var oversCounterView: CounterView
    private lateinit var bowlsCounterView: CounterView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.overs_back.setOnClickListener{oversListener()?.onOversDismissClicked()}
        okView = view.overs_ok
        okView.setOnClickListener{
            presenter.saveOvers()
        }
        oversCounterView = view.overs_counter
        oversCounterView.counterListener = counterOversListener(presenter)
        bowlsCounterView = view.bowls_counter
        bowlsCounterView.counterListener = counterBowlsListener(presenter)

        presenter.startPresenting()
    }

    override fun disableDecrementOversCount(disable: Boolean) {
        oversCounterView.disableDecrement(disable)
    }

    override fun disableDecrementBowlsCount(disable: Boolean) {
        bowlsCounterView.disableDecrement(disable)
    }

    override fun setOversCount(value: String) {
        oversCounterView.setIndicatorValue(value)
    }

    override fun setBowlsCount(value: String) {
        bowlsCounterView.setIndicatorValue(value)
    }

    override fun enableOk(enable: Boolean) {
        okView.isEnabled = enable
    }

    override fun dismiss() {
        oversListener()?.onOversConfirmed(presenter.overs)
    }

    override fun onStop() {
        super.onStop()
        presenter.stopPresenting()
    }

    private fun counterOversListener(presenter: OversPresenter) : CounterListener {
        return object : CounterListener {
            override fun onIncrementClicked() {
                presenter.incrementOversCount()
            }

            override fun onDecrementClicked() {
                presenter.decrementOversCount()
            }
        }
    }

    private fun counterBowlsListener(presenter: OversPresenter) : CounterListener {
        return object : CounterListener {
            override fun onIncrementClicked() {
                presenter.incrementBowsCount()
            }

            override fun onDecrementClicked() {
                presenter.decrementBowlsCount()
            }
        }
    }

    override fun getName() = "OversFragment"

    private fun oversListener() : OversListener? {
        return if (activity is OversListener) activity as OversListener else null
    }
}