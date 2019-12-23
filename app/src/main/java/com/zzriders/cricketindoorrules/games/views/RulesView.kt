package com.zzriders.cricketindoorrules.games.views

import com.zzriders.cricketindoorrules.games.adapters.RulesAdaptee

interface RulesView {
    fun displayRules(rules: List<RulesAdaptee>)
}