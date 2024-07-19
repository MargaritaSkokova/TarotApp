package com.example.tarotapp.db.information.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tarotapp.db.information.entities.LoveStatisticsEntity
import java.util.UUID

@Dao
interface LoveStatisticsDao {
    @Insert
    fun insert(statisticsEntity: LoveStatisticsEntity)

    @Delete
    fun delete(statisticsEntity: LoveStatisticsEntity)

    @Query("SELECT * FROM love_statistics WHERE id = :id")
    fun findById(id: UUID): LoveStatisticsEntity?

    @Query("SELECT * FROM love_statistics")
    fun findAll(): List<LoveStatisticsEntity>?
}