package com.zzriders.cricketindoorrules.games.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RulesAdapterViewHolder<E>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(elt: E)
}