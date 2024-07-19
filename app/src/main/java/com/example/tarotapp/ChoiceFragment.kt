package com.example.tarotapp

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tarotapp.databinding.FragmentChoiceBinding
import com.example.tarotapp.db.information.InformationRepository
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


class ChoiceFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    val informationRepository = InformationRepository.get()

    private var _binding: FragmentChoiceBinding? = null
    val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChoiceBinding.inflate(inflater, container, false)
        val view = binding.root

        val motionLayout = binding.motionLayout

        binding.backButton.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (motionLayout.currentState == R.id.center_daily) {
                    view.findNavController()
                        .navigate(
                            ChoiceFragmentDirections.actionChoiceFragmentToLobbyFragment()
                        )
                }
            }
            false
        }

        binding.buttonChoice.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (motionLayout.currentState == R.id.deck_daily) {
                    if (informationRepository.isTodayDailyReading()) {
                        val snackbar = Snackbar.make(this.requireContext(),
                            view,
                            "Sorry, you can have only one daily reading per day. You can view it in your profile history.",
                            BaseTransientBottomBar.LENGTH_SHORT).setAction("GO") {
                            view.findNavController()
                                .navigate(
                                    ChoiceFragmentDirections.actionChoiceFragmentToProfileFragment()
                                )
                        }
                        snackbar.setBackgroundTint(ContextCompat.getColor(this.requireContext(), R.color.md_theme_light_onSurface))
                        snackbar.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.md_theme_dark_onSurface))
                        val params = snackbar.view.layoutParams as (FrameLayout.LayoutParams)
                        params.gravity = Gravity.CENTER
                        params.width = FrameLayout.LayoutParams.WRAP_CONTENT
                        snackbar.view.layoutParams = params
                        val textView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                        textView.setCompoundDrawablesWithIntrinsicBounds(com.google.android.material.R.drawable.mtrl_ic_error, 0, 0, 0)
                        textView.setMaxLines(3)
                        snackbar.show()
                    } else {
                        view.findNavController()
                            .navigate(
                                ChoiceFragmentDirections.actionChoiceFragmentToReadingFragment(
                                    "Daily"
                                )
                            )
                    }
                }
                else if (motionLayout.currentState == R.id.deck_love) {
                    view.findNavController()
                        .navigate(ChoiceFragmentDirections.actionChoiceFragmentToReadingFragment("Love"))
                } else if (motionLayout.currentState == R.id.deck_work) {
                    view.findNavController()
                        .navigate(ChoiceFragmentDirections.actionChoiceFragmentToReadingFragment("Work"))
                } else if (motionLayout.currentState == R.id.deck_weekly) {
                    if (informationRepository.didThisWeekHaveReading()) {
                        val snackbar = Snackbar.make(this.requireContext(),
                            view,
                            "Sorry, you can have only one weekly reading per week. You can view it in your profile history.",
                            BaseTransientBottomBar.LENGTH_SHORT).setAction("GO") {
                            view.findNavController()
                                .navigate(
                                    ChoiceFragmentDirections.actionChoiceFragmentToProfileFragment()
                                )
                        }
                        snackbar.setBackgroundTint(ContextCompat.getColor(this.requireContext(), R.color.md_theme_light_onSurface))
                        snackbar.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.md_theme_dark_onSurface))
                        val params = snackbar.view.layoutParams as (FrameLayout.LayoutParams)
                        params.gravity = Gravity.CENTER
                        params.width = FrameLayout.LayoutParams.WRAP_CONTENT
                        snackbar.view.layoutParams = params
                        val textView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                        textView.setCompoundDrawablesWithIntrinsicBounds(com.google.android.material.R.drawable.mtrl_ic_error, 0, 0, 0)
                        textView.setMaxLines(3)
                        snackbar.show()
                    }
                    else {
                    view.findNavController()
                        .navigate(ChoiceFragmentDirections.actionChoiceFragmentToReadingFragment("Weekly"))
                    }
                } else if (motionLayout.currentState == R.id.deck_ask_your_question) {
                    view.findNavController()
                        .navigate(ChoiceFragmentDirections.actionChoiceFragmentToReadingFragment("Ask your question"))
                }
            }
            false
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}