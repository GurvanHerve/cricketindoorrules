package com.zzriders.cricketindoorrules.games.converters

import com.zzriders.cricketindoorrules.games.adapters.RulesAdaptee
import com.zzriders.cricketindoorrules.games.database.model.Rules
import com.zzriders.cricketindoorrules.games.database.model.Rules.BonusRuns

class RulesToBonusRunConverter : Converter<Rules, BonusRuns> {

    override fun convert(input: Rules): BonusRuns? {
        for (bonus in BonusRuns.values()) {
            if (bonus.value == input.name) {
                return bonus
            }
        }
        return null
    }
}