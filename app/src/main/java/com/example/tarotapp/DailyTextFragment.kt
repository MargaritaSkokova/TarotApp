package com.example.tarotapp

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.tarotapp.databinding.FragmentDailyTextBinding
import com.example.tarotapp.db.cards.CardsRepository
import com.example.tarotapp.db.information.InformationRepository

class DailyTextFragment : Fragment() {
    var _bindind: FragmentDailyTextBinding? = null
    val binding get() = _bindind!!
    val informationRepository = InformationRepository.get()
    val cardsRepository = CardsRepository.get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindind = FragmentDailyTextBinding.inflate(inflater, container, false)
        val view = binding.root
        val textDaily: String =
        if (!informationRepository.isTodayDailyReading()) {
            "Looks like you haven't done daily reading today... Still you can have one right now! Go on and find out what waits you today!"
        } else {
            cardsRepository.findDailyReading(
                cardsRepository.findCardByName(
                    informationRepository.findAllDailyStatistics().last().cardFirst
                ).id,
                cardsRepository.findCardByName(
                    informationRepository.findAllDailyStatistics().last().cardSecond
                ).id
            ).meaning
        }

        binding.textDaily.text = textDaily
        return view
    }
}