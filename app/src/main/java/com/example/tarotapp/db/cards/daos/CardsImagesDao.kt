package com.example.tarotapp.db.cards.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tarotapp.db.cards.entities.CardsImagesEntity

@Dao
interface CardsImagesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(cardImages: List<CardsImagesEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cardImage: CardsImagesEntity)

    @Query("SELECT * FROM cards_images where card_id = :card")
    fun findByCardId(card: Int): CardsImagesEntity

    @Query("SELECT image_name FROM cards_images where card_id = :card")
    fun findByCardIdImage(card: Int): String
}