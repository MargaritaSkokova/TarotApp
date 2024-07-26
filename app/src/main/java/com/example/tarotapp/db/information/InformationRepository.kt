package com.example.tarotapp.db.information

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tarotapp.db.information.entities.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.WeekFields
import java.util.Date
import java.util.Locale
import java.util.UUID

class InformationRepository private constructor(context: Context) {
    private val database: InformationDatabase = Room.databaseBuilder(
        context.applicationContext,
        InformationDatabase::class.java,
        "InformationDb"
    ).addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            db.execSQL("INSERT INTO profile VALUES ('${UUID.randomUUID()}','username', '${LocalDate.now()}', '${LocalDate.of(1970, 1, 1)}', null)")
        }
    }).allowMainThreadQueries().build()

    private val answerStatisticsDao = database.answerStatisticsDao()
    private val dailyStatisticsDao = database.dailyStatisticsDao()
    private val loveStatisticsDao = database.loveStatisticsDao()
    private val profileDao = database.profileDao()
    private val weeklyStatisticsDao = database.weeklyStatisticsDao()
    private val workStatisticsDao = database.workStatisticsDao()

    companion object {
        private var instance: InformationRepository? = null

        fun initialize(context: Context) {
            if (instance == null) {
                instance = InformationRepository(context)
            }
        }

        fun get(): InformationRepository {
            return instance ?: throw IllegalArgumentException()
        }
    }

    fun insertAnswerStatistics(statisticsEntity: AnswerStatisticsEntity) {
        answerStatisticsDao.insert(statisticsEntity)
    }

    fun deleteAnswerStatistics(statisticsEntity: AnswerStatisticsEntity) {
        answerStatisticsDao.delete(statisticsEntity)
    }

    fun findByIdAnswerStatistics(id: UUID): AnswerStatisticsEntity {
        val entity = answerStatisticsDao.findById(id) ?: throw IllegalArgumentException()
        return entity
    }

    fun insertDailyStatistics(statisticsEntity: DailyStatisticsEntity) {
        dailyStatisticsDao.insert(statisticsEntity)
    }

    fun deleteDailyStatistics(statisticsEntity: DailyStatisticsEntity) {
        dailyStatisticsDao.delete(statisticsEntity)
    }

    fun findByIdDailyStatistics(id: UUID): DailyStatisticsEntity {
        val entity = dailyStatisticsDao.findById(id) ?: throw IllegalArgumentException()
        return entity
    }

    fun findByDateDailyStatistics(dateToday: LocalDate): DailyStatisticsEntity? {
        val entities = dailyStatisticsDao.findAll() ?: return null
        for (entity in entities) {
            if (entity.dateReading == dateToday) {
                return entity
            }
        }

        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isTodayDailyReading(): Boolean {
        val dateToday = LocalDate.now()
        val dailyStatics = findByDateDailyStatistics(dateToday)
        return dailyStatics != null
    }

    fun insertLoveStatistics(statisticsEntity: LoveStatisticsEntity) {
        loveStatisticsDao.insert(statisticsEntity)
    }

    fun deleteLoveStatistics(statisticsEntity: LoveStatisticsEntity) {
        loveStatisticsDao.delete(statisticsEntity)
    }

    fun findByIdLoveStatistics(id: UUID): LoveStatisticsEntity {
        val entity = loveStatisticsDao.findById(id) ?: throw IllegalArgumentException()
        return entity
    }

    fun insertWeeklyStatistics(statisticsEntity: WeeklyStatisticsEntity) {
        weeklyStatisticsDao.insert(statisticsEntity)
    }

    fun deleteWeeklyStatistics(statisticsEntity: WeeklyStatisticsEntity) {
        weeklyStatisticsDao.delete(statisticsEntity)
    }

    fun findByIdWeeklyStatistics(id: UUID): WeeklyStatisticsEntity {
        val entity = weeklyStatisticsDao.findById(id) ?: throw IllegalArgumentException()
        return entity
    }

    fun didThisWeekHaveReading(): Boolean {
        val wow = WeekFields.of(Locale.getDefault()).weekOfYear()
        val today = LocalDate.now()
        val numberWeekToday = today.get(wow)
        val weeklyStatics = weeklyStatisticsDao.findAll() ?: return true
        for (statistic in weeklyStatics) {
            if (statistic.dateReading.get(wow) == numberWeekToday) {
                return true
            }
        }

        return false
    }

    fun insertWorkStatistics(statisticsEntity: WorkStatisticsEntity) {
        workStatisticsDao.insert(statisticsEntity)
    }

    fun deleteWorkStatistics(statisticsEntity: WorkStatisticsEntity) {
        workStatisticsDao.delete(statisticsEntity)
    }

    fun findByIdWorkStatistics(id: UUID): WorkStatisticsEntity {
        val entity = workStatisticsDao.findById(id) ?: throw IllegalArgumentException()
        return entity
    }

    fun insertProfile(entity: ProfileEntity) {
        profileDao.insert(entity)
    }

    fun deleteProfile(entity: ProfileEntity) {
        profileDao.delete(entity)
    }

    fun findAllProfile(): List<ProfileEntity> {
        val entity = profileDao.findAll() ?: throw IllegalArgumentException()
        return entity
    }

    fun findAllAnswerStatistics(): List<AnswerStatisticsEntity> {
        val entity = answerStatisticsDao.findAll() ?: throw IllegalArgumentException()
        return entity
    }

    fun findAllDailyStatistics(): List<DailyStatisticsEntity> {
        val entity = dailyStatisticsDao.findAll() ?: throw IllegalArgumentException()
        return entity
    }

    fun findAllLoveStatistics(): List<LoveStatisticsEntity> {
        val entity = loveStatisticsDao.findAll() ?: throw IllegalArgumentException()
        return entity
    }

    fun findAllWeeklyStatistics(): List<WeeklyStatisticsEntity> {
        val entity = weeklyStatisticsDao.findAll() ?: throw IllegalArgumentException()
        return entity
    }

    fun findAllWorkStatistics(): List<WorkStatisticsEntity> {
        val entity = workStatisticsDao.findAll() ?: throw IllegalArgumentException()
        return entity
    }
}