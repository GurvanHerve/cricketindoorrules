package com.zzriders.cricketindoorrules.games.presenters

import com.zzriders.cricketindoorrules.games.adapters.AbsRulesAdaptee
import com.zzriders.cricketindoorrules.games.converters.Converter
import com.zzriders.cricketindoorrules.games.database.model.Rules
import com.zzriders.cricketindoorrules.games.database.repositories.RulesRepository
import com.zzriders.cricketindoorrules.games.views.RulesView

class RulesPresenter(private val view: RulesView,
                     private val rulesRepository: RulesRepository,
                     private val gameUid: String,
                     private val rulesToRulesAdapteeConverter: Converter<List<Rules>, List<AbsRulesAdaptee>>
) {

    fun startPresenting() {
        rulesRepository.get(gameUid)
            .map { rulesToRulesAdapteeConverter.convert(it) }
            .subscribe { adaptees -> view.displayRules(adaptees!!) }
    }
}