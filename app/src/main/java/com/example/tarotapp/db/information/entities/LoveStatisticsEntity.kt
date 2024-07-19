package com.example.tarotapp.db.information.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date
import java.util.UUID

@Entity(tableName = "love_statistics")
data class LoveStatisticsEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "date_reading") val dateReading: LocalDate,
    @ColumnInfo(name = "card_first") val cardFirst: String,
    @ColumnInfo(name = "card_second") val cardSecond: String,
    @ColumnInfo(name = "card_third") val cardThird: String
)
