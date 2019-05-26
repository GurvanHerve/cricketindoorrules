package com.zzriders.cricketindoorrules.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zzriders.cricketindoorrules.R
import kotlinx.android.synthetic.main.home_fragment.view.*

class HomeFragment : BaseFragment() {

    interface HomeListener {
        fun onGameClicked()
        fun onNewTypeGameClicked()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.home_new_type_game.setOnClickListener { homeListener()?.onNewTypeGameClicked() }
    }

    override fun getName() = "HomeFragment"

    private fun homeListener() : HomeListener? {
        return if (activity is HomeListener) activity as HomeListener else null
    }
}
