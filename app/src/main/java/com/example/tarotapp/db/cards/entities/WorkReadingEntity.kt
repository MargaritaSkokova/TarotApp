package com.example.tarotapp.db.cards.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "work_reading"
)
data class WorkReadingEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "first_card_id") val firstCardId: Int,
    @ColumnInfo(name = "second_card_id") val secondCardId: Int,
    val meaning: String
)