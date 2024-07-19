package com.example.tarotapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.tarotapp.db.cards.CardsRepository
import com.example.tarotapp.db.cards.entities.CardsImages
import com.example.tarotapp.db.information.InformationRepository
import com.example.tarotapp.db.information.entities.AnswerStatisticsEntity
import com.example.tarotapp.db.information.entities.DailyStatisticsEntity
import com.example.tarotapp.db.information.entities.LoveStatisticsEntity
import com.example.tarotapp.db.information.entities.WeeklyStatisticsEntity
import com.example.tarotapp.db.information.entities.WorkStatisticsEntity
import java.time.LocalDate

class TarotReadingViewModel : ViewModel() {
    private val cardsRepository = CardsRepository.get()
    private val informationRepository = InformationRepository.get()
    var isReadingDone = false
    lateinit var readingObject: Pair<List<CardsImages>, List<String>>

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCardsByReading(readingType: String, numberCardsToChose: Int): Pair<List<CardsImages>, List<String>> {
        val cards: MutableList<CardsImages> = mutableListOf()
        val numbersUsed: MutableList<Int> = mutableListOf()
        for (i in 0..<numberCardsToChose) {
            var numberCard = (0..21).random()
            while (numbersUsed.indexOf(numberCard) != -1) {
                numberCard = (0..21).random()
            }

            numbersUsed.add(numberCard)
            val card = cardsRepository.findCardById(numberCard)
            cards.add(CardsImages(numberCard, card.name, cardsRepository.findCardsImage(numberCard)))
        }

        val meaning: MutableList<String> = mutableListOf()
        when(readingType) {
            "Love" -> {
                var reading = cardsRepository.findLoveReading(cards[0].id)
                meaning.add("Relationships now:\n" + reading.meaningFirst)
                reading = cardsRepository.findLoveReading(cards[1].id)
                meaning.add("Difficulties:\n" + reading.meaningSecond)
                reading = cardsRepository.findLoveReading(cards[2].id)
                meaning.add("Nearest future:\n" + reading.meaningSecond)
                informationRepository.insertLoveStatistics(LoveStatisticsEntity(dateReading = LocalDate.now(),
                    cardFirst = cards[0].name, cardSecond = cards[1].name, cardThird = cards[2].name))
            }
            "Ask your question" -> {
                val reading = cardsRepository.findAskReading(cards[0].id)
                meaning.addAll(listOf("Yes/No: " + reading.yesNoAnswer, "Why: " + reading.whyAnswer, "How: " + reading.howAnswer))
                informationRepository.insertAnswerStatistics(AnswerStatisticsEntity(dateReading = LocalDate.now(), card = cards[0].name))
            }
            "Daily" -> {
                val reading = cardsRepository.findDailyReading(cards[0].id, cards[1].id)
                meaning.add(reading.meaning)
                informationRepository.insertDailyStatistics(DailyStatisticsEntity(dateReading = LocalDate.now(), cardFirst = cards[0].name, cardSecond = cards[1].name))
            }
            "Weekly" -> {
                val reading = cardsRepository.findWeeklyReading(cards[0].id, cards[1].id)
                meaning.add(reading.meaning)
                informationRepository.insertWeeklyStatistics(WeeklyStatisticsEntity(dateReading = LocalDate.now(), cardFirst = cards[0].name, cardSecond = cards[1].name))
            }
            "Work" -> {
                val reading = cardsRepository.findWorkReading(cards[0].id, cards[1].id)
                meaning.add(reading.meaning)
                informationRepository.insertWorkStatistics(WorkStatisticsEntity(dateReading = LocalDate.now(), cardFirst = cards[0].name, cardSecond = cards[1].name))
            }
        }

        isReadingDone = true
        readingObject = Pair(cards, meaning)
        return readingObject
    }
}