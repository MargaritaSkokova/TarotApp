package com.example.tarotapp.db.cards.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tarotapp.db.cards.entities.AnswerReadingEntity

@Dao
interface AnswerReadingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(readings: List<AnswerReadingEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(reading: AnswerReadingEntity)

    @Query("SELECT * FROM answer_reading where id = :id")
    fun findByCards(id: Int): AnswerReadingEntity
}