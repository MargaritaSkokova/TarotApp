package com.example.tarotapp.db.cards

import com.example.tarotapp.db.cards.entities.AnswerReadingEntity
import com.example.tarotapp.db.cards.entities.CardsEntity
import com.example.tarotapp.db.cards.entities.CardsImagesEntity
import com.example.tarotapp.db.cards.entities.DailyReadingEntity
import com.example.tarotapp.db.cards.entities.LoveReadingEntity
import com.example.tarotapp.db.cards.entities.WeeklyReadingEntity
import com.example.tarotapp.db.cards.entities.WorkReadingEntity
import com.example.tarotapp.db.cards.files.AskReading
import com.example.tarotapp.db.cards.files.Cards
import com.example.tarotapp.db.cards.files.CardsImages
import com.example.tarotapp.db.cards.files.DailyReading
import com.example.tarotapp.db.cards.files.LoveReading
import com.example.tarotapp.db.cards.files.WeeklyReading
import com.example.tarotapp.db.cards.files.WorkReading

class DataFilesParser {
    companion object {
        fun parseCards(): List<CardsEntity> {
            val lines: List<String> = Cards.cards.split("\n")
            val listCards: MutableList<CardsEntity> = mutableListOf()
            for (line in lines) {
                val index = line.indexOf("\'")
                val numberCard = line.substring(0..<index - 1).toInt()
                val name = line.substring(index + 1..<line.length - 1)
                val newCard = CardsEntity(numberCard, name)
                listCards.add(newCard)
            }

            return listCards
        }

        fun parseCardsImages(): List<CardsImagesEntity> {
            val lines: List<String> = CardsImages.cardsImages.split("\n")
            val listCards: MutableList<CardsImagesEntity> = mutableListOf()
            for (line in lines) {
                val index = line.indexOf(",")
                val numberCard = line.substring(0..<index).toInt()
                val name = line.substring(index + 1..<line.length)
                val newCard = CardsImagesEntity(cardId = numberCard, imageName = name)
                listCards.add(newCard)
            }

            return listCards
        }

        private fun parseReadingsTwoCards(text: String): List<Triple<Int, Int, String>> {
            val lines: List<String> = text.split("\n")
            val readings: MutableList<Triple<Int, Int, String>> = mutableListOf()
            for (line in lines) {
                var index = line.indexOf(",")
                val firstCard = line.substring(0..<index).toInt()
                val newLine = line.substring(index + 1)
                index = newLine.indexOf(",")
                val secondCard = newLine.substring(0..<index).toInt()
                val reading = newLine.substring(index + 2..<newLine.length - 1)
                readings.add(Triple(firstCard, secondCard, reading))
            }

            return readings
        }

        private fun parseReadingsThreeCards(text: String): List<Pair<Int, Triple<String, String, String>>> {
            val lines: List<String> = text.split("\n")
            val readings: MutableList<Pair<Int, Triple<String, String, String>>> = mutableListOf()
            var j = 0
            for (i in 0..21) {
                val number = lines[j].toInt()
                j++
                val readingFirst = lines[j]
                j++
                val readingSecond = lines[j]
                j++
                val readingThird = lines[j]
                j++
                readings.add(Pair(number, Triple(readingFirst, readingSecond, readingThird)))
            }

            return readings
        }

        fun parseDaily(): List<DailyReadingEntity> {
            val readingParams = parseReadingsTwoCards(DailyReading.dailyReading)
            val readings: MutableList<DailyReadingEntity> = mutableListOf()
            for (params in readingParams) {
                readings.add(
                    DailyReadingEntity(
                        firstCardId = params.first,
                        secondCardId = params.second,
                        meaning = params.third
                    )
                )
            }

            return readings
        }

        fun parseWork(): List<WorkReadingEntity> {
            val readingParams = parseReadingsTwoCards(WorkReading.workReading)
            val readings: MutableList<WorkReadingEntity> = mutableListOf()
            for (params in readingParams) {
                readings.add(
                    WorkReadingEntity(
                        firstCardId = params.first,
                        secondCardId = params.second,
                        meaning = params.third
                    )
                )
            }

            return readings
        }

        fun parseWeekly(): List<WeeklyReadingEntity> {
            val readingParams = parseReadingsTwoCards(WeeklyReading.weeklyReading)
            val readings: MutableList<WeeklyReadingEntity> = mutableListOf()
            for (params in readingParams) {
                readings.add(
                    WeeklyReadingEntity(
                        firstCardId = params.first,
                        secondCardId = params.second,
                        meaning = params.third
                    )
                )
            }

            return readings
        }

        fun parseLove(): List<LoveReadingEntity> {
            val readingParams = parseReadingsThreeCards(LoveReading.loveReading)
            val readings: MutableList<LoveReadingEntity> = mutableListOf()
            for (params in readingParams) {
                readings.add(
                    LoveReadingEntity(
                        params.first,
                        params.second.first,
                        params.second.second,
                        params.second.third
                    )
                )
            }

            return readings
        }

        fun parseAsk(): List<AnswerReadingEntity> {
            val readingParams = parseReadingsThreeCards(AskReading.askReading)
            val readings: MutableList<AnswerReadingEntity> = mutableListOf()
            for (params in readingParams) {
                readings.add(
                    AnswerReadingEntity(
                        params.first,
                        params.second.first,
                        params.second.second,
                        params.second.third
                    )
                )
            }

            return readings
        }
    }
}