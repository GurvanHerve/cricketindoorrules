package com.zzriders.cricketindoorrules.games.views

import com.zzriders.cricketindoorrules.games.adapters.AbsRulesAdaptee

interface RulesView {
    fun displayRules(rules: List<AbsRulesAdaptee>)
}