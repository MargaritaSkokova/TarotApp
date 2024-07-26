package com.example.tarotapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.tarotapp.databinding.FragmentLobbyBinding
import com.example.tarotapp.db.cards.CardsRepository
import com.example.tarotapp.db.information.InformationRepository
import java.time.LocalTime


class LobbyFragment : Fragment() {
    val informationRepository = InformationRepository.get()
    val cardsRepository = CardsRepository.get()
    var _binding: FragmentLobbyBinding? = null
    val binding get() = _binding!!
    lateinit var adapter: DoubleReadingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLobbyBinding.inflate(inflater, container, false)
        val view = binding.root
        adapter = DoubleReadingAdapter(this)

        binding.pager.adapter = adapter
        binding.readingButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_lobbyFragment_to_choiceFragment)
        }
        binding.profileButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_lobbyFragment_to_profileFragment)
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

        return view
    }
}