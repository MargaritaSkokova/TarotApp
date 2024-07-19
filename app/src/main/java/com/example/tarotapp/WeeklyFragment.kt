package com.example.tarotapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tarotapp.databinding.FragmentWeeklyBinding
import com.example.tarotapp.db.information.InformationRepository

class WeeklyFragment : Fragment() {
    private var _binding: FragmentWeeklyBinding? = null
    val binding get() = _binding!!
    private val informationRepository = InformationRepository.get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeeklyBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val statistics = informationRepository.findAllWeeklyStatistics()
        var text = ""
        var count = 1
        for (statistic in statistics) {
            text += "$count. "
            text += "Date: ${statistic.dateReading}\n"
            text += "   First card: ${statistic.cardFirst}\n"
            text += "   Second card: ${statistic.cardSecond}\n"
            text += "\n"
            count++
        }

        binding.textView.text = text
    }
}