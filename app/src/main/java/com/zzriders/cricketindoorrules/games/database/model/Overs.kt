package com.zzriders.cricketindoorrules.games.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="overs")
class Overs {
    @PrimaryKey
    var uid = UUID.randomUUID().toString()

    @ColumnInfo(name="overs_count")
    var oversCount = 0

    @ColumnInfo(name="bowls_count")
    var bowlsCount = 0
}