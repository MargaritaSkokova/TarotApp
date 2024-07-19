package com.example.tarotapp.db.information.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tarotapp.db.information.entities.WeeklyStatisticsEntity
import java.util.Date
import java.util.UUID

@Dao
interface WeeklyStatisticsDao {
    @Insert
    fun insert(statisticsEntity: WeeklyStatisticsEntity)

    @Delete
    fun delete(statisticsEntity: WeeklyStatisticsEntity)

    @Query("SELECT * FROM weekly_statistics WHERE id = :id")
    fun findById(id: UUID): WeeklyStatisticsEntity?

    @Query("SELECT * FROM weekly_statistics")
    fun findAll(): List<WeeklyStatisticsEntity>?
}