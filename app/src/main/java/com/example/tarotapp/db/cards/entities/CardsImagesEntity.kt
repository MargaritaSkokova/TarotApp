package com.example.tarotapp.db.cards.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "cards_images"
)
data class CardsImagesEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "card_id") val cardId: Int,
    @ColumnInfo(name = "image_name") val imageName: String
)