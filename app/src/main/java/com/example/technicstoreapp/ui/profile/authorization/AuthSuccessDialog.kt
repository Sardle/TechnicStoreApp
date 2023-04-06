package com.example.technicstoreapp.ui.profile.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.technicstoreapp.databinding.FragmentAuthSuccessBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthSuccessDialog : Fragment() {

    private var _binding: FragmentAuthSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.comeToProfile.setOnClickListener {
            val action = AuthSuccessDialogDirections.actionAuthSuccessDialogToNavigationProfile()
            findNavController().navigate(action)
        }
    }
}