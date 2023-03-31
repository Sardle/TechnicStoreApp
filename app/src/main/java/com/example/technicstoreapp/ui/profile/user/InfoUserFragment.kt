package com.example.technicstoreapp.ui.profile.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.technicstoreapp.databinding.FragmentInfoUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoUserFragment : Fragment() {

    private var _binding: FragmentInfoUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<InfoUserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPage()
        logOutUser()
    }

    private fun logOutUser() {
        binding.logOutAccount.setOnClickListener {
            viewModel.logOutUser()
            comeToProfilePage()
        }
    }

    private fun comeToProfilePage() {
        val action = InfoUserFragmentDirections.actionInfoUserFragmentToNavigationProfile()
        findNavController().navigate(action)
    }

    private fun setupPage() {
        with(viewModel) {
            getUser()

            userLiveData.observe(viewLifecycleOwner) {
                binding.nameUserPage.text = it.name
                binding.emailUser.text = it.email
                binding.dateOfBirthUser.text = it.dateOfBirth
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}