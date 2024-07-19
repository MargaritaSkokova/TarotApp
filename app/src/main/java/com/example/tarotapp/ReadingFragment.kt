package com.example.tarotapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.tarotapp.databinding.FragmentReadingBinding

var numberCardsChosen = 0

class ReadingFragment : Fragment() {
    private var _binding: FragmentReadingBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReadingBinding.inflate(inflater, container, false)
        val view = binding.root

        val readingType = ReadingFragmentArgs.fromBundle(requireArguments()).readingType

        val textReadingType =
            if (readingType == "Ask your question") {
                "Ask your question"
            } else {
                "$readingType reading"
            }

        val cardsToChoose: Int
        when (readingType) {
            "Ask your question" -> cardsToChoose = 1
            "Love" -> cardsToChoose = 3
            else -> {
                cardsToChoose = 2
            }
        }

        binding.readingType.text = textReadingType

        val textChooseCards = binding.chooseText
        textChooseCards.text = "Choose ${cardsToChoose - numberCardsChosen} cards"

        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.imageView1.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView2.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView3.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView4.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView5.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView6.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView7.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView8.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView9.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView10.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView11.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView12.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView13.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView14.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView15.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView16.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView17.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView18.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView19.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView20.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))
        binding.imageView21.setOnTouchListener(ImageTouchListener(navController, textChooseCards, cardsToChoose, readingType))

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        numberCardsChosen = 0
        _binding = null
    }
}

class ImageTouchListener(val navGraph: NavController, val textView: TextView, val cardsToChoose: Int, val readingType : String) : View.OnTouchListener {

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            view.animate()
                .x(view.x + 50)
                .y(view.y + 50)
                .setDuration(250)
                .start()

            val changeTime = 250L
            view.postDelayed(
                Runnable { view.visibility = View.GONE },
                changeTime
            )

            numberCardsChosen++
            textView.text = "Choose ${cardsToChoose - numberCardsChosen} cards"
            if (numberCardsChosen == cardsToChoose) {
                val changeTime = 250L
                view.postDelayed(
                    Runnable { navGraph.navigate(ReadingFragmentDirections.actionReadingFragmentToTarotReadingFragment(readingType, cardsToChoose)) },
                    changeTime
                )
            }
        }
        return false
    }
}
