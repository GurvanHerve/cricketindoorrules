package com.zzriders.cricketindoorrules.games.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="rules")
class Rules {
    @PrimaryKey
    private val uid = UUID.randomUUID()
}