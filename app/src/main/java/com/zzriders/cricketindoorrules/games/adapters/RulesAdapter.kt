package com.zzriders.cricketindoorrules.games.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zzriders.cricketindoorrules.R
import com.zzriders.cricketindoorrules.games.viewholders.RulesAdapterViewHolder
import com.zzriders.cricketindoorrules.games.viewholders.RulesHeaderViewHolder
import com.zzriders.cricketindoorrules.games.viewholders.RulesViewHolder

class RulesAdapter : RecyclerView.Adapter<RulesAdapterViewHolder<AbsRulesAdaptee>>() {

    private val BONUS_RUNS_VIEW_TYPE = 0
    private val DISMISSALS_VIEW_TYPE = 1
    private val RULES_VIEW_TYPE = 2

    private val rules = ArrayList<AbsRulesAdaptee>()
    private var bonusRunsCount = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulesAdapterViewHolder<AbsRulesAdaptee> {
        return when (viewType) {
            BONUS_RUNS_VIEW_TYPE -> {
                RulesHeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_rules_item_header, parent, false))
            }
            DISMISSALS_VIEW_TYPE -> {
                RulesHeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_rules_item_header, parent, false))
            }
            else -> {
                RulesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_rules_item, parent, false))
            }
        }
    }

    override fun getItemCount() = this.rules.size + 2

    override fun onBindViewHolder(holder: RulesAdapterViewHolder<AbsRulesAdaptee>, position: Int) {
        when {
            getItemViewType(position) ==  RULES_VIEW_TYPE -> {
                holder.bind(this.rules[position])
            }
            position == 0 -> {
                holder.bind()
            }
            else -> {
                holder.bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                BONUS_RUNS_VIEW_TYPE
            }
            (bonusRunsCount + 2) -> {
                DISMISSALS_VIEW_TYPE
            }
            else -> {
                RULES_VIEW_TYPE
            }
        }
    }

    fun setRules(rules: List<AbsRulesAdaptee>) {
        this.rules.clear()
        this.rules.addAll(rules)
        notifyDataSetChanged()
    }
}