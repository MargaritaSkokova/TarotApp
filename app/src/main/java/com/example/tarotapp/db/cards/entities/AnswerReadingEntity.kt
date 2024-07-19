package com.example.tarotapp.db.cards.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "answer_reading"
)
data class AnswerReadingEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "yes_no_answer") val yesNoAnswer: String,
    @ColumnInfo(name = "why_answer") val whyAnswer: String,
    @ColumnInfo(name = "how_answer") val howAnswer: String
)