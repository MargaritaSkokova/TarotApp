package com.example.tarotapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tarotapp.databinding.FragmentTarotReadingBinding
import com.example.tarotapp.db.cards.entities.CardsImages

class TarotReadingFragment : Fragment() {
    private var _binding: FragmentTarotReadingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TarotReadingViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTarotReadingBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[TarotReadingViewModel::class.java]

        val readingType = TarotReadingFragmentArgs.fromBundle(requireArguments()).readingType
        val numberCards = TarotReadingFragmentArgs.fromBundle(requireArguments()).numberCards

        val cardsReading: Pair<List<CardsImages>, List<String>>
        if (!viewModel.isReadingDone) {
            cardsReading = viewModel.getCardsByReading(readingType, numberCards)
        } else {
            cardsReading = viewModel.readingObject
        }

        when (numberCards) {
            2 -> {
                binding.card2.visibility = View.GONE
                binding.card1.rotation = 0.0f
                binding.card3.rotation = 0.0f
                binding.card1.setImageResource(
                    resources.getIdentifier(
                        cardsReading.first[0].imageName,
                        "drawable",
                        this.context?.packageName
                    )
                )
                binding.card3.setImageResource(
                    resources.getIdentifier(
                        cardsReading.first[1].imageName,
                        "drawable",
                        this.context?.packageName
                    )
                )
            }
            1 -> {
                binding.card1.visibility = View.GONE
                binding.card3.visibility = View.GONE
                binding.card2.setImageResource(
                    resources.getIdentifier(
                        cardsReading.first[0].imageName,
                        "drawable",
                        this.context?.packageName
                    )
                )
            }
            else -> {
                binding.card1.setImageResource(
                    resources.getIdentifier(
                        cardsReading.first[0].imageName,
                        "drawable",
                        this.context?.packageName
                    )
                )
                binding.card2.setImageResource(
                    resources.getIdentifier(
                        cardsReading.first[1].imageName,
                        "drawable",
                        this.context?.packageName
                    )
                )
                binding.card3.setImageResource(
                    resources.getIdentifier(
                        cardsReading.first[2].imageName,
                        "drawable",
                        this.context?.packageName
                    )
                )
            }
        }

        var cardNames = "Your cards: "
        for (card in cardsReading.first) {
            cardNames += card.name
            cardNames += ", "
        }

        cardNames = cardNames.dropLast(2)

        var textMeaning = cardNames + "\n\n"
        for (text in cardsReading.second) {
            textMeaning += text
            textMeaning += "\n\n"
        }

        textMeaning = textMeaning.dropLast(2)
        binding.textMeaning.text = textMeaning

        binding.backStartBitton.setOnClickListener {
            findNavController().navigate(R.id.action_tarotReadingFragment_to_lobbyFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}