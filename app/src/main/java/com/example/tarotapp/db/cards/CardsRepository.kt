package com.example.tarotapp.db.cards

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tarotapp.db.cards.daos.*
import com.example.tarotapp.db.cards.entities.*
import com.example.tarotapp.db.cards.files.Cards

class CardsRepository private constructor(context: Context) {
    private val database: CardsDatabase = Room.databaseBuilder(
        context.applicationContext,
        CardsDatabase::class.java,
        "CardsDb"
    ).addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val cards = DataFilesParser.parseCards()
            val cardsImages = DataFilesParser.parseCardsImages()
            val loveReading = DataFilesParser.parseLove()
            val dailyReading = DataFilesParser.parseDaily()
            val askReading = DataFilesParser.parseAsk()
            val weeklyReading = DataFilesParser.parseWeekly()
            val workReading = DataFilesParser.parseWork()

            for (element in cards) {
                db.execSQL("INSERT INTO cards VALUES (${element.id}, '${element.name}')")
            }

            for (element in cardsImages) {
                db.execSQL("INSERT INTO cards_images VALUES ('${element.id}', ${element.cardId}, '${element.imageName}')")
            }

            for (element in loveReading) {
                db.execSQL("INSERT INTO love_reading VALUES (${element.id}, '${element.meaningFirst}', '${element.meaningSecond}', '${element.meaningThird}')")
            }

            for (element in askReading) {
                db.execSQL("INSERT INTO answer_reading VALUES (${element.id}, '${element.yesNoAnswer}', '${element.whyAnswer}', '${element.howAnswer}')")
            }

            for (element in dailyReading) {
                db.execSQL("INSERT INTO daily_reading VALUES ('${element.id}', ${element.firstCardId}, ${element.secondCardId}, '${element.meaning}')")
            }

            for (element in workReading) {
                db.execSQL("INSERT INTO work_reading VALUES ('${element.id}', ${element.firstCardId}, ${element.secondCardId}, '${element.meaning}')")
            }

            for (element in weeklyReading) {
                db.execSQL("INSERT INTO weekly_reading VALUES ('${element.id}', ${element.firstCardId}, ${element.secondCardId}, '${element.meaning}')")
            }
        }
    }).allowMainThreadQueries().build()

    private val cardsDao: CardsDao = database.cardsDao()
    private val cardsImagesDao: CardsImagesDao = database.cardsImagesDao()
    private val dailyReadingDao: DailyReadingDao = database.dailyReadingDao()
    private val loveReadingDao: LoveReadingDao = database.loveReadingDao()
    private val weeklyReadingDao: WeeklyReadingDao = database.weeklyReadingDao()
    private val workReadingDao: WorkReadingDao = database.workReadingDao()
    private val answerReadingDao: AnswerReadingDao = database.answerReadingDao()


    companion object {
        private var instance: CardsRepository? = null

        fun initialize(context: Context) {
            if (instance == null) {
                instance = CardsRepository(context)
            }
        }

        fun get(): CardsRepository {
            return instance ?: throw IllegalArgumentException()
        }
    }

    fun findCardById(id: Int): CardsEntity {
        return cardsDao.findById(id)
    }

    fun findDailyReading(firstCard: Int, secondCard: Int): DailyReadingEntity {
        return dailyReadingDao.findByCards(firstCard, secondCard)
    }

    fun findLoveReading(cardId: Int): LoveReadingEntity {
        return loveReadingDao.findByCards(cardId)
    }

    fun findAskReading(cardId: Int): AnswerReadingEntity {
        return answerReadingDao.findByCards(cardId)
    }

    fun findWorkReading(firstCard: Int, secondCard: Int): WorkReadingEntity {
        return workReadingDao.findByCards(firstCard, secondCard)
    }

    fun findWeeklyReading(firstCard: Int, secondCard: Int): WeeklyReadingEntity {
        return weeklyReadingDao.findByCards(firstCard, secondCard)
    }
    fun findCardsImage(id: Int): String {
        return cardsImagesDao.findByCardIdImage(id)
    }

    fun findCardByName(name: String): CardsEntity {
        return cardsDao.findByName(name)
    }
}