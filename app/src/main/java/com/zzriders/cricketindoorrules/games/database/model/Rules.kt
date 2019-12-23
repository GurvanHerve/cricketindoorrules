package com.zzriders.cricketindoorrules.games.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="rules",
    foreignKeys = [
        ForeignKey(
            entity = Game::class,
            parentColumns = ["uid"],
            childColumns = ["game_uid"],
            onDelete = ForeignKey.CASCADE
        )]
)
class Rules {

    enum class BonusRuns(val value: String, val order: Int) {
        FRONT_NET("frontNet", 0),
        STRICKET("stricket", 1),
        BOWLERS("bowlers", 2),
        BACK_NET_ON_THE_BOUNCE("backNetOnTheBounce", 3),
        BACK_NET_ON_THE_FULL("backNetOnTheFull", 4),
        TOUCH_WALL("touchWall", 5)
    }

    enum class Dismissals(val value: String, val order: Int) {
        LWB_AND_MANKAD("lwbAndMankad", 0),
        BREAK_THE_STUMPS("breakTheStumps", 1),
        TOUCH_THE_CEILING("touchTheCeiling", 2),
        CAUGHT("caught", 3),
        WIDE("wide", 4),
        NO_BALL("noBall", 5)
    }

    @PrimaryKey
    var uid = UUID.randomUUID().toString()

    @ColumnInfo(name = "game_uid")
    var gameUid = ""

    @ColumnInfo(name = "name")
    var name = ""

    @ColumnInfo(name = "value")
    var value = 0
}