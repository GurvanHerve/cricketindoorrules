package com.zzriders.cricketindoorrules.games.presenters

import com.zzriders.cricketindoorrules.games.converters.RulesToRulesAdapteeConverter
import com.zzriders.cricketindoorrules.games.database.model.Rules
import com.zzriders.cricketindoorrules.games.database.model.Rules.BonusRuns
import com.zzriders.cricketindoorrules.games.database.repositories.RulesRepository
import com.zzriders.cricketindoorrules.games.views.RulesView

class RulesPresenter(private val view: RulesView,
                     private val rulesRepository: RulesRepository,
                     private val gameUid: String,
                     private val rulesToRulesAdapteeConverter: RulesToRulesAdapteeConverter
) {

    fun startPresenting() {
        rulesRepository.get(gameUid)
            .map {
                BonusRuns.values()
                val bonus = rulesToRulesAdapteeConverter.conver(it) ?:
                if (bonus) {

                }
            }
            .defaultIfEmpty()
            .toList()
            .subscribe {  }
    }
}