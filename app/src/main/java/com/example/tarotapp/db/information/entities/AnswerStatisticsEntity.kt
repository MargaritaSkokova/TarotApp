package com.example.tarotapp.db.information.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.tarotapp.db.information.LocalDateConverter
import java.time.LocalDate
import java.util.Date
import java.util.UUID

@Entity(tableName = "answer_statistics")
data class AnswerStatisticsEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "date_reading") val dateReading: LocalDate,
    val card: String
)
