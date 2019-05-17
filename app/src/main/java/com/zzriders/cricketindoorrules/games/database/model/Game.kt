package com.zzriders.cricketindoorrules.games.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="game",
        foreignKeys = [
            ForeignKey(
                entity = Team::class,
                parentColumns = ["uid"],
                childColumns = ["team_two_uid"],
                onDelete = CASCADE
            ),
            ForeignKey(
                entity = Team::class,
                parentColumns = ["uid"],
                childColumns = ["team_one_uid"],
                onDelete = CASCADE
            ),
            ForeignKey(
                entity = Overs::class,
                parentColumns = ["uid"],
                childColumns = ["overs_uid"],
                onDelete = CASCADE
            )
])
class Game {
    @PrimaryKey
    var uid = UUID.randomUUID().toString()

    @ColumnInfo(name="name")
    var name: String = ""

    @ColumnInfo(name="team_one_uid")
    var teamOneUid = ""

    @ColumnInfo(name="team_two_uid")
    var teamTwoUid = ""

    @ColumnInfo(name="overs_uid")
    var oversUid = ""
}