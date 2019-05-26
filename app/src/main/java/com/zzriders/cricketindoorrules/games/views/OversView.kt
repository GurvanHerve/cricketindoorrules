package com.zzriders.cricketindoorrules.games.views

interface OversView {
    fun disableDecrementOversCount(disable: Boolean)
    fun disableDecrementBowlsCount(disable: Boolean)
    fun setOversCount(value: String)
    fun setBowlsCount(value: String)
    fun enableOk(enable: Boolean)
    fun dismiss()
}