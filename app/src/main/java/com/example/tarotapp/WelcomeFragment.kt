package com.example.tarotapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tarotapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val view = binding.root
//        binding.welcomeStartButton.setOnClickListener {
//            view.findNavController().navigate(R.id.action_welcomeFragment_to_choiceFragment)
//        }
//
//        binding.welcomeProfileButton.setOnClickListener {
//            view.findNavController().navigate(R.id.action_welcomeFragment_to_profileFragment)
//        }

        val textAnim = binding.textShine

        val animX = ObjectAnimator.ofFloat(textAnim, "scaleX", 1.2f)
        animX.repeatMode = ValueAnimator.REVERSE
        animX.repeatCount = ValueAnimator.INFINITE
        animX.setDuration(2000L)
        val animY = ObjectAnimator.ofFloat(textAnim, "scaleY", 1.2f)
        animY.repeatMode = ValueAnimator.REVERSE
        animY.repeatCount = ValueAnimator.INFINITE
        animY.setDuration(2000L)
        animX.start()
        animY.start()

        view.setOnClickListener{
            animX.pause()
            animY.pause()
            animX.setDuration(200L)
            animY.setDuration(200L)
            animX.start()
            animY.start()
            view.postDelayed(Runnable { view.findNavController().navigate(R.id.action_welcomeFragment_to_lobbyFragment) },
                200L)

        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}