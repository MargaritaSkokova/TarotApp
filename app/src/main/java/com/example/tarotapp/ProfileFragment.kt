package com.example.tarotapp

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.tarotapp.databinding.FragmentProfileBinding
import com.example.tarotapp.db.information.InformationRepository
import com.example.tarotapp.db.information.LocalDateConverter
import com.example.tarotapp.db.information.entities.ProfileEntity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.time.LocalDate

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    val binding get() = _binding!!
    val informationRepository = InformationRepository.get()

    private lateinit var imageView: ImageView
    private lateinit var adapter: TestAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    private var thisUri: Uri? = null
    private val tabNames: Array<String> = arrayOf(
        "Daily", "Love", "Weekly", "Work", "Ask question"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pickMedia =
            this.registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null) {
                    Glide.with(requireContext()).load(uri).into(imageView)
                    thisUri = uri
                }
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        imageView = binding.imageView

        adapter = TestAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = adapter

        tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

        val profile = informationRepository.findAllProfile()
        if (profile.isEmpty()) {
            binding.birth.text = "1970-01-01"
            binding.loginDate.text = "1970-01-01"
            binding.nickname.text = "username"
        } else {
            binding.birth.text = profile.last().dateBirth.toString()
            binding.loginDate.text = profile.last().dateStart.toString()
            binding.nickname.text = profile.last().nickname
            if (profile.last().uriPicture != null) {
                Glide.with(requireContext()).load(Uri.parse(profile.last().uriPicture))
                    .into(imageView)
            }
        }

        binding.birthEdit.visibility = View.GONE
        binding.nicknameEdit.visibility = View.GONE
        binding.saveButton.visibility = View.GONE
        binding.buttonChangePhoto.visibility = View.GONE

        binding.buttonEdit.setOnClickListener {
            binding.buttonEdit.visibility = View.GONE
            binding.birthEdit.visibility = View.VISIBLE
            binding.nicknameEdit.visibility = View.VISIBLE
            binding.saveButton.visibility = View.VISIBLE
            binding.buttonChangePhoto.visibility = View.VISIBLE

            binding.birthEdit.setText(binding.birth.text)
            binding.nicknameEdit.setText(binding.nickname.text)
            binding.birth.visibility = View.INVISIBLE
            binding.nickname.visibility = View.INVISIBLE
        }

        binding.nicknameEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                if (input.isNotEmpty()) {
                    if (input.length > 15) {
                        binding.nicknameEdit.error = "15 symbols max"
                    } else {
                        binding.nicknameEdit.error = null
                    }
                }
            }
        })

        binding.birthEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                if (input.isNotEmpty()) {
                    if (!input.matches(Regex("([0-9][0-9][0-9][0-9])-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])"))) {
                        binding.birthEdit.error = "YYYY-MM-DD format"
                    } else {
                        binding.birthEdit.error = null
                    }
                }
            }
        })

        binding.buttonChangePhoto.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.saveButton.setOnClickListener {
            if (binding.nicknameEdit.text.length <= 15 && (binding.birthEdit.text.isEmpty() || binding.birthEdit.text.matches(
                    Regex("([0-9][0-9][0-9][0-9])-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])")
                ))
            ) {
                val newNicknameEditable = binding.nicknameEdit.text
                var newNickname: String?
                newNickname = newNicknameEditable.toString()
                val newDateEditable = binding.birthEdit.text.toString()
                var newDate: LocalDate?
                if (newDateEditable == "") {
                    newDate = null
                } else {
                    newDate = LocalDateConverter.toDate(newDateEditable)
                }

                val previousProfile = informationRepository.findAllProfile()

                if (previousProfile.isNotEmpty()) {
                    if (newNickname.isEmpty()) {
                        newNickname = previousProfile.last().nickname
                    }

                    if (newDate == null) {
                        newDate = previousProfile.last().dateBirth
                    }

                    if (thisUri == null) {
                        thisUri = Uri.parse(previousProfile.last().uriPicture)
                    }
                } else {
                    if (newNickname.isEmpty()) {
                        newNickname = "username"
                    }

                    if (newDate == null) {
                        newDate = LocalDate.of(1970, 1, 1)
                    }
                }

                informationRepository.insertProfile(
                    ProfileEntity(
                        nickname = newNickname,
                        dateStart = LocalDateConverter.toDate(binding.loginDate.text.toString())!!,
                        dateBirth = newDate!!,
                        uriPicture = thisUri.toString()
                    )
                )

                binding.birthEdit.visibility = View.GONE
                binding.nicknameEdit.visibility = View.GONE
                binding.saveButton.visibility = View.GONE
                binding.birth.visibility = View.VISIBLE
                if (newDate == null) {
                    binding.birth.text = "1970-01-01"
                } else {
                    binding.birth.text = newDate.toString()
                }
                binding.nickname.visibility = View.VISIBLE
                if (newNickname == null) {
                    binding.nickname.text = "username"
                } else {
                    binding.nickname.text = newNickname
                }
                binding.buttonEdit.visibility = View.VISIBLE
                binding.buttonChangePhoto.visibility = View.GONE
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_lobbyFragment)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}