package com.example.tarotapp.db.information.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tarotapp.db.information.entities.WorkStatisticsEntity
import java.util.UUID

@Dao
interface WorkStatisticsDao {
    @Insert
    fun insert(statisticsEntity: WorkStatisticsEntity)

    @Delete
    fun delete(statisticsEntity: WorkStatisticsEntity)

    @Query("SELECT * FROM work_statistics WHERE id = :id")
    fun findById(id: UUID): WorkStatisticsEntity?

    @Query("SELECT * FROM work_statistics")
    fun findAll(): List<WorkStatisticsEntity>?
}