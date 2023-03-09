package com.example.technicstoreapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentTechnicPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TechnicPageFragment : Fragment() {

    private var _binding: FragmentTechnicPageBinding? = null
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

        val addCart = "В корзину за " + args.price

        with(binding) {
            nameHeading.text = args.name
            nameTechnic.text = args.name
            description.text = args.description
            getPoster(args.imageUrl, imageTechnicPage)
            addToCart.text = addCart
        }

        binding.back.setOnClickListener {
//            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    findNavController().popBackStack()
//                }
//            })
            requireActivity().onBackPressed()
        }
    }

    private fun getPoster(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }
}