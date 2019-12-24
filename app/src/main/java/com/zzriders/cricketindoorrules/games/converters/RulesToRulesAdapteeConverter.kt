package com.zzriders.cricketindoorrules.games.converters

import com.zzriders.cricketindoorrules.games.adapters.AbsRulesAdaptee
import com.zzriders.cricketindoorrules.games.adapters.RulesAdaptee
import com.zzriders.cricketindoorrules.games.adapters.RulesAdapteeHeader
import com.zzriders.cricketindoorrules.games.database.model.Rules

class RulesToRulesAdapteeConverter(private val rulesToRulesAdaptee: Converter<Rules, RulesAdaptee>,
                                   private val bonusRunToRulesAdapteeConverter: Converter<Rules.BonusRuns, RulesAdaptee>,
                                   private val dismissalsToRulesAdapteeConverter: Converter<Rules.Dismissals, RulesAdaptee>)
    : Converter<List<Rules>, List<AbsRulesAdaptee>> {

    override fun convert(rules: List<Rules>): List<AbsRulesAdaptee> {
        val adaptees = ArrayList<AbsRulesAdaptee>()

        bonusRuns(rules, adaptees)
        dismissals(rules, adaptees)

        return adaptees
    }

    private fun bonusRuns(rules: List<Rules>, adaptees: MutableList<AbsRulesAdaptee>) {
        adaptees.add(RulesAdapteeHeader.BonusRuns())

        var adaptee: RulesAdaptee?
        if (rules.isEmpty()) {
            for (bonusRun in Rules.BonusRuns.values()) {
                adaptee = bonusRunToRulesAdapteeConverter.convert(bonusRun)
                if (adaptee != null) adaptees.add(adaptee)
            }
        } else {
            for (rule in rules) {
                adaptee = rulesToRulesAdaptee.convert(rule)
                if (adaptee != null) adaptees.add(adaptee)
            }
        }
    }

    private fun dismissals(rules: List<Rules>, adaptees: MutableList<AbsRulesAdaptee>) {
        adaptees.add(RulesAdapteeHeader.BonusRuns())

        var adaptee: RulesAdaptee?
        if (rules.isEmpty()) {
            for (bonusRun in Rules.Dismissals.values()) {
                adaptee = dismissalsToRulesAdapteeConverter.convert(bonusRun)
                if (adaptee != null) adaptees.add(adaptee)
            }
        } else {
            for (rule in rules) {
                adaptee = rulesToRulesAdaptee.convert(rule)
                if (adaptee != null) adaptees.add(adaptee)
            }
        }
    }
}