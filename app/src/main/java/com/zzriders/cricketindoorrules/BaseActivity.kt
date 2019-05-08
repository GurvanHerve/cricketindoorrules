package com.zzriders.cricketindoorrules

import androidx.appcompat.app.AppCompatActivity
import com.zzriders.cricketindoorrules.home.BaseFragment


abstract class BaseActivity : AppCompatActivity() {

    protected fun showFragment(fragmentContainer: Int, fragment: BaseFragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(R.anim.anim_slide_up, 0, 0, R.anim.anim_slide_down)
        if (addToBackStack) transaction?.addToBackStack(fragment.getName())
        transaction?.replace(fragmentContainer, fragment)
        transaction?.commit()
    }

    protected fun dismissFragment() {
        supportFragmentManager.popBackStack()
    }
}

