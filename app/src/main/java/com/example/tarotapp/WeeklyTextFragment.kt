package com.example.tarotapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.tarotapp.databinding.FragmentWeeklyTextBinding
import com.example.tarotapp.db.cards.CardsRepository
import com.example.tarotapp.db.information.InformationRepository

class WeeklyTextFragment : Fragment() {
    var _bindind: FragmentWeeklyTextBinding? = null
    val binding get() = _bindind!!
    val informationRepository = InformationRepository.get()
    val cardsRepository = CardsRepository.get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindind = FragmentWeeklyTextBinding.inflate(inflater, container, false)
        val view = binding.root

        val textWeekly: String =
            if (informationRepository.didThisWeekHaveReading()) {
                cardsRepository.findWeeklyReading(
                    cardsRepository.findCardByName(
                        informationRepository.findAllWeeklyStatistics().last().cardFirst
                    ).id,
                    cardsRepository.findCardByName(
                        informationRepository.findAllWeeklyStatistics().last().cardSecond
                    ).id
                ).meaning
            } else {
                "This week might have been tough since you haven't had weekly reading done. Get one right now!"
            }

        binding.textWeekly.text = textWeekly
        return view
    }
}