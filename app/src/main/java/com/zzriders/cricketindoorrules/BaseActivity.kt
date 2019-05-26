package com.zzriders.cricketindoorrules

import androidx.appcompat.app.AppCompatActivity
import com.zzriders.cricketindoorrules.home.BaseFragment


abstract class BaseActivity : AppCompatActivity() {

    protected fun replaceFragment(fragmentContainer: Int, fragment: BaseFragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(R.anim.anim_slide_up, 0, 0, R.anim.anim_slide_down)
        transaction?.replace(fragmentContainer, fragment, fragment.getName())
        if (addToBackStack) transaction?.addToBackStack(fragment.getName())
        transaction?.commit()
    }

    protected fun addFragment(fragmentContainer: Int, fragment: BaseFragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(R.anim.anim_slide_up, 0, 0, R.anim.anim_slide_down)
        transaction?.add(fragmentContainer, fragment, fragment.getName())
        if (addToBackStack) transaction?.addToBackStack(fragment.getName())
        transaction?.commit()
    }

    protected fun dismissDisplayedFragment() {
        supportFragmentManager.popBackStack()
    }
}

