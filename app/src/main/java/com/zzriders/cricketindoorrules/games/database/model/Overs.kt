package com.zzriders.cricketindoorrules.games.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="overs")
class Overs {
    @PrimaryKey
    private val uid = UUID.randomUUID()

    @ColumnInfo(name="overs_count")
    private var oversCount = 0

    @ColumnInfo(name="bowls_count")
    private var bowlsCount = 0
}