package com.zzriders.cricketindoorrules.games.views

interface PlayersView {
    fun disableDecrementPlayerTeamOne(disable: Boolean)
    fun disableDecrementPlayerTeamTwo(disable: Boolean)
    fun setPlayerCountTeamOne(value: String)
    fun setPlayerCountTeamTwo(value: String)
    fun enableOk(enable: Boolean)
}