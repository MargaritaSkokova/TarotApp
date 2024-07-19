package com.example.tarotapp.db.information.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tarotapp.db.information.entities.DailyStatisticsEntity
import java.util.Date
import java.util.UUID

@Dao
interface DailyStatisticsDao {
    @Insert
    fun insert(statisticsEntity: DailyStatisticsEntity)

    @Delete
    fun delete(statisticsEntity: DailyStatisticsEntity)

    @Query("SELECT * FROM daily_statistics WHERE id = :id")
    fun findById(id: UUID): DailyStatisticsEntity?

    @Query("SELECT * FROM daily_statistics")
    fun findAll(): List<DailyStatisticsEntity>?
}