package com.example.tarotapp.db.cards.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tarotapp.db.cards.entities.LoveReadingEntity

@Dao
interface LoveReadingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(readings: List<LoveReadingEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(reading: LoveReadingEntity)

    @Query("SELECT * FROM love_reading where id = :id")
    fun findByCards(id: Int): LoveReadingEntity
}