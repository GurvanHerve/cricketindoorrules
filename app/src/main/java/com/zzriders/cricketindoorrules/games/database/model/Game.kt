package com.zzriders.cricketindoorrules.games.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="game")
@ForeignKey(entity = Overs::class, parentColumns = ["uid"], childColumns = ["overs_uid"])
class Game {
    @PrimaryKey
    var uid = UUID.randomUUID().toString()

    @ColumnInfo(name="name")
    var name: String = ""

//    @ColumnInfo(name="team")
//    val team = mutableListOf<Team>()

    @ColumnInfo(name="overs_uid")
    var overs: String = ""
}