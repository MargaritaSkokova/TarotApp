package com.example.tarotapp.db.cards

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tarotapp.db.cards.daos.AnswerReadingDao
import com.example.tarotapp.db.cards.daos.CardsDao
import com.example.tarotapp.db.cards.daos.CardsImagesDao
import com.example.tarotapp.db.cards.daos.DailyReadingDao
import com.example.tarotapp.db.cards.daos.LoveReadingDao
import com.example.tarotapp.db.cards.daos.WeeklyReadingDao
import com.example.tarotapp.db.cards.daos.WorkReadingDao
import com.example.tarotapp.db.cards.entities.AnswerReadingEntity
import com.example.tarotapp.db.cards.entities.CardsEntity
import com.example.tarotapp.db.cards.entities.CardsImagesEntity
import com.example.tarotapp.db.cards.entities.DailyReadingEntity
import com.example.tarotapp.db.cards.entities.LoveReadingEntity
import com.example.tarotapp.db.cards.entities.WeeklyReadingEntity
import com.example.tarotapp.db.cards.entities.WorkReadingEntity

@Database(
    version = 1,
    entities = [AnswerReadingEntity::class,
        CardsEntity::class,
        CardsImagesEntity::class,
        DailyReadingEntity::class,
        LoveReadingEntity::class,
        WeeklyReadingEntity::class,
        WorkReadingEntity::class]
)
abstract class CardsDatabase : RoomDatabase() {
    abstract fun cardsDao(): CardsDao
    abstract fun cardsImagesDao(): CardsImagesDao
    abstract fun dailyReadingDao(): DailyReadingDao
    abstract fun loveReadingDao(): LoveReadingDao
    abstract fun weeklyReadingDao(): WeeklyReadingDao
    abstract fun workReadingDao(): WorkReadingDao
    abstract fun answerReadingDao(): AnswerReadingDao
}