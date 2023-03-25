package com.example.technicstoreapp.ui.technic_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.technicstoreapp.databinding.FragmentTechnicPageBinding
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

        val technic = viewModel.getTechnicInfo(args.id)
        val addCart = "В корзину за " + technic.price + " р."

        val colors = viewModel.getColorsTechnic(technic.id)

        binding.firstColor.text = colors.first()
        binding.secondColor.text = colors[1]
        binding.thirdColor.text = colors.last()

        var selectedColor = colors.first()


        with(binding) {
            nameHeading.text = technic.name
            nameTechnic.text = technic.name
            description.text = technic.description
            getPoster(
                viewModel.getImageTechnic(technic.id, firstColor.text.toString()),
                imageTechnicPage
            )
            addToCart.text = addCart
        }

        binding.toggle.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)
            selectedColor = selectedRadioButton?.text.toString()
            getPoster(
                viewModel.getImageTechnic(technic.id, selectedColor),
                binding.imageTechnicPage
            )
        }

        binding.addToCart.setOnClickListener {
            viewModel.insertTechnicToCart(technic, selectedColor)
        }

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    private fun getPoster(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }
}