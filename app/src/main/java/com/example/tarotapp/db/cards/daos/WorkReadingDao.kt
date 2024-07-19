package com.example.tarotapp.db.cards.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tarotapp.db.cards.entities.WorkReadingEntity

@Dao
interface WorkReadingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(readings: List<WorkReadingEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(reading: WorkReadingEntity)

    @Query("SELECT * FROM work_reading where (first_card_id = :firstCard and second_card_id = :secondCard) " +
            "or (first_card_id = :secondCard and second_card_id = :firstCard)")
    fun findByCards(firstCard: Int, secondCard: Int): WorkReadingEntity
}