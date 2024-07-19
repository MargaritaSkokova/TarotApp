package com.example.tarotapp.db.cards.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "love_reading"
)
data class LoveReadingEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "meaning_first") val meaningFirst: String,
    @ColumnInfo(name = "meaning_second") val meaningSecond: String,
    @ColumnInfo(name = "meaning_third") val meaningThird: String
)