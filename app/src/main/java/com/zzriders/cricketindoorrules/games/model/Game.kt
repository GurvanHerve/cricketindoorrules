package com.zzriders.cricketindoorrules.games.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="game")
class Game() {
    @PrimaryKey val uid = UUID.randomUUID()
    @ColumnInfo(name="name") val name = ""
    @ColumnInfo(name="team") val team = mutableListOf<Team>()
    @ColumnInfo(name="overs") val overs = Overs()
}