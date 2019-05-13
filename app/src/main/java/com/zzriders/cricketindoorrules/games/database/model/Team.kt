package com.zzriders.cricketindoorrules.games.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="team")
class Team {
    @PrimaryKey
    val uid = UUID.randomUUID()

    @ColumnInfo(name="name")
    var name = ""

    @ColumnInfo(name="count")
    var playersCount = 0
}