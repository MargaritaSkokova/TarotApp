package com.example.tarotapp.db.information.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tarotapp.db.information.entities.AnswerStatisticsEntity
import java.util.Date
import java.util.UUID

@Dao
interface AnswerStatisticsDao {
    @Insert
    fun insert(statisticsEntity: AnswerStatisticsEntity)

    @Delete
    fun delete(statisticsEntity: AnswerStatisticsEntity)

    @Query("SELECT * FROM answer_statistics WHERE id = :id")
    fun findById(id: UUID): AnswerStatisticsEntity?

    @Query("SELECT * FROM answer_statistics")
    fun findAll(): List<AnswerStatisticsEntity>?
}