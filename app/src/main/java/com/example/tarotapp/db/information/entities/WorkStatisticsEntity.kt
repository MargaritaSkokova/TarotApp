package com.example.tarotapp.db.information.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date
import java.util.UUID

@Entity(tableName = "work_statistics")
data class WorkStatisticsEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "date_reading") val dateReading: LocalDate,
    @ColumnInfo(name = "card_first") val cardFirst: String,
    @ColumnInfo(name = "card_second") val cardSecond: String
)
