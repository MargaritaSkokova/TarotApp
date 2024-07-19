package com.example.tarotapp.db.information

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tarotapp.db.information.daos.AnswerStatisticsDao
import com.example.tarotapp.db.information.daos.DailyStatisticsDao
import com.example.tarotapp.db.information.daos.LoveStatisticsDao
import com.example.tarotapp.db.information.daos.ProfileDao
import com.example.tarotapp.db.information.daos.WeeklyStatisticsDao
import com.example.tarotapp.db.information.daos.WorkStatisticsDao
import com.example.tarotapp.db.information.entities.AnswerStatisticsEntity
import com.example.tarotapp.db.information.entities.DailyStatisticsEntity
import com.example.tarotapp.db.information.entities.LoveStatisticsEntity
import com.example.tarotapp.db.information.entities.ProfileEntity
import com.example.tarotapp.db.information.entities.WeeklyStatisticsEntity
import com.example.tarotapp.db.information.entities.WorkStatisticsEntity

@Database(
    version = 1,
    entities = [AnswerStatisticsEntity::class,
        DailyStatisticsEntity::class,
        LoveStatisticsEntity::class,
        ProfileEntity::class,
        WeeklyStatisticsEntity::class,
        WorkStatisticsEntity::class]
)
@TypeConverters(LocalDateConverter::class)
abstract class InformationDatabase : RoomDatabase() {
    abstract fun answerStatisticsDao(): AnswerStatisticsDao
    abstract fun dailyStatisticsDao(): DailyStatisticsDao
    abstract fun loveStatisticsDao(): LoveStatisticsDao
    abstract fun profileDao(): ProfileDao
    abstract fun weeklyStatisticsDao(): WeeklyStatisticsDao
    abstract fun workStatisticsDao(): WorkStatisticsDao
}