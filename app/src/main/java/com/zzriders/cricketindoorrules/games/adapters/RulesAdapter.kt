package com.zzriders.cricketindoorrules.games.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zzriders.cricketindoorrules.R
import com.zzriders.cricketindoorrules.games.viewholders.RulesViewHolder

class RulesAdapter : RecyclerView.Adapter<RulesViewHolder>() {

    private val rules = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulesViewHolder
            = RulesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_rules_item, parent, false))

    override fun getItemCount() = this.rules.size

    override fun onBindViewHolder(holder: RulesViewHolder, position: Int) {
        holder.bind(this.rules[position])
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun setRules(rules: List<String>) {
        this.rules.clear()
        this.rules.addAll(rules)
        notifyDataSetChanged()
    }

}