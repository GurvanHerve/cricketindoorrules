package com.zzriders.cricketindoorrules.games.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="team")
class Team {
    @PrimaryKey
    var uid = UUID.randomUUID().toString()

    @ColumnInfo(name="name")
    var name = ""

    @ColumnInfo(name="players_count")
    var playersCount = 0
}