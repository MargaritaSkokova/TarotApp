package com.example.tarotapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tarotapp.databinding.FragmentLobbyBinding
import com.example.tarotapp.db.cards.CardsRepository
import com.example.tarotapp.db.information.InformationRepository
import com.skydoves.powermenu.MenuAnimation
import com.skydoves.powermenu.OnMenuItemClickListener
import com.skydoves.powermenu.PowerMenu
import com.skydoves.powermenu.PowerMenuItem
import java.time.LocalDate
import java.time.LocalTime


class LobbyFragment : Fragment() {
    val informationRepository = InformationRepository.get()
    val cardsRepository = CardsRepository.get()
    var _binding: FragmentLobbyBinding? = null
    val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLobbyBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.newReadingButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_lobbyFragment_to_choiceFragment)
        }

        val powerMenu = PowerMenu.Builder(requireContext())
            .addItem(PowerMenuItem("Profile & History", false))
            .addItem(PowerMenuItem("Library", false))
            .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT)
            .setMenuRadius(10f)
            .setMenuShadow(10f) // sets the shadow.
            .setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.md_theme_dark_onTertiary
                )
            )
            .setTextGravity(Gravity.CENTER)
            .setTextSize(20)
            .setTextTypeface(Typeface.create("serif", Typeface.NORMAL))
            .setSelectedTextColor(Color.WHITE)
            .setMenuColor(Color.GRAY)
            .setSelectedMenuColor(ContextCompat.getColor(requireContext(), R.color.status_bar))
            .build()
        val listener = OnMenuItemClickListener { position: Int, item: PowerMenuItem ->
            if (item.title == "Profile & History") {
                view.findNavController().navigate(R.id.action_lobbyFragment_to_profileFragment)
                powerMenu.dismiss()
            }
        }
        powerMenu.setOnMenuItemClickListener(listener)
        powerMenu.showAsDropDown(binding.buttonMenu)
        binding.buttonMenu.setOnClickListener {
            powerMenu.showAsDropDown(it)
        }

        var textWelcome: String = if (LocalTime.now().hour < 6) {
            "Good night"
        } else if (LocalTime.now().hour < 12) {
            "Good morning"
        } else if (LocalTime.now().hour < 18) {
            "Good afternoon"
        } else {
            "Good evening"
        }

        textWelcome += ", ${informationRepository.findAllProfile().last().nickname}!"
        binding.textGreeting.text = textWelcome

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

        val textWeekly: String =
            if (informationRepository.didThisWeekHaveReading()) {
                cardsRepository.findWeeklyReading(
                    cardsRepository.findCardByName(
                    informationRepository.findAllWeeklyStatistics().last().cardFirst
                ).id,
                    cardsRepository.findCardByName(
                        informationRepository.findAllWeeklyStatistics().last().cardSecond
                    ).id).meaning
            } else {
                "This week might have been tough since you haven't had weekly reading done. Get one right now!"
            }

        binding.textWeekly.text = textWeekly

        return view
    }
}