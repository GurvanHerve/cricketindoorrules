package com.zzriders.cricketindoorrules.home

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun getName() : String {
        return javaClass.simpleName
    }
}