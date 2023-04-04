package com.example.technicstoreapp.ui.technic_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.allViews
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentTechnicPageBinding
import com.example.technicstoreapp.domain.TechnicData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TechnicPageFragment : Fragment() {

    private var _binding: FragmentTechnicPageBinding? = null
    private val viewModel by viewModels<TechnicPageViewModel>()
    private val binding get() = _binding!!
    private val args: TechnicPageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTechnicPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTechnicInfo(args.id)

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            checkLoading(it)
        }

        viewModel.technicLiveData.observe(viewLifecycleOwner) {

            if (it != null) {
                val technic = it
                val defaultColor = args.defaultColor
                val colors = technic.colors.keys.toList()

                setupColors(colors)
                var selectedColor = getDefaultColor(defaultColor, colors)
                setupPage(technic, selectedColor)

                binding.toggle.setOnCheckedChangeListener { _, checkedId ->
                    val selectedRadioButton = binding.toggle.findViewById<RadioButton>(checkedId)
                    selectedColor = selectedRadioButton?.text.toString()
                    getPoster(
                        technic.colors[selectedColor].toString(),
                        binding.imageTechnicPage
                    )
                }

                binding.addToCart.setOnClickListener {
                    viewModel.insertTechnicToCart(technic, selectedColor)
                }

                back()
            }
        }
    }

    private fun back() {
        binding.back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupPage(technic: TechnicData, selectedColor: String) {
        with(binding) {
            nameHeading.text = technic.name
            nameTechnic.text = technic.name
            description.text = technic.description
            getPoster(
                technic.colors[selectedColor].toString(),
                imageTechnicPage
            )
            addToCart.text = getString(R.string.add_to_cart, technic.price.toString())
        }
    }

    private fun getDefaultColor(defaultColor: String, colors: List<String>): String {
        val selectedColor: String
        when (defaultColor) {
            "default" -> {
                selectedColor = colors.first()
            }
            colors[1] -> {
                selectedColor = colors[1]
                binding.toggle.check(binding.secondColor.id)
            }
            else -> {
                selectedColor = colors.last()
                binding.toggle.check(binding.thirdColor.id)
            }
        }
        return selectedColor
    }

    private fun setupColors(colors: List<String>) {
        with(binding) {
            firstColor.text = colors.first()
            secondColor.text = colors[1]
            thirdColor.text = colors.last()
        }
    }

    private fun checkLoading(exists: Boolean) {
        for (view in requireView().allViews) {
            if (view is ProgressBar) {
                view.isVisible = exists
            } else if (view !is ConstraintLayout){
                view.isVisible = !exists
            }
        }
    }

    private fun getPoster(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }
}