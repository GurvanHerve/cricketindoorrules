package com.zzriders.cricketindoorrules.games.converters

import com.zzriders.cricketindoorrules.games.adapters.RulesAdaptee
import com.zzriders.cricketindoorrules.games.database.model.Rules

class RulesToDismissalsConverter : Converter<Rules, Rules.Dismissals> {

    override fun convert(input: Rules): Rules.Dismissals? {
        for (dismissals in Rules.Dismissals.values()) {
            if (dismissals.value == input.name) {
                return dismissals
            }
        }
        return null
    }
}