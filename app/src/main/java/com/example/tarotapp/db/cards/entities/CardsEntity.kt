package com.example.tarotapp.db.cards.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardsEntity(
    @PrimaryKey val id: Int,
    val name: String
)