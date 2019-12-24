package com.zzriders.cricketindoorrules.games.converters

import com.zzriders.cricketindoorrules.games.adapters.RulesAdaptee
import com.zzriders.cricketindoorrules.games.database.model.Rules

class RulesToRulesAdaptee : Converter<Rules, RulesAdaptee> {

    override fun convert(input: Rules): RulesAdaptee? {
        RulesAdaptee()
    }
}