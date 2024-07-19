package com.example.tarotapp.db.cards.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tarotapp.db.cards.entities.CardsEntity

@Dao
interface CardsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(cards: List<CardsEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCard(card: CardsEntity)

    @Query("SELECT * FROM cards where cards.id = :id")
    fun findById(id: Int): CardsEntity

    @Query("SELECT * FROM cards where cards.name = :name")
    fun findByName(name: String): CardsEntity

    @Query("SELECT * FROM cards")
    fun getAllCards(): List<CardsEntity>
}