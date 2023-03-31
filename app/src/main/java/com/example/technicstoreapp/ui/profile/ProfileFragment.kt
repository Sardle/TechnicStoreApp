package com.example.technicstoreapp.ui.profile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentCatalogBinding
import com.example.technicstoreapp.databinding.FragmentProfileBinding
import com.example.technicstoreapp.domain.UserData
import com.example.technicstoreapp.ui.catalog.CatalogViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onStart() {
        super.onStart()
        with(viewModel) {
            checkAvailabilityUser()
            checkLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    binding.root.isVisible = false
                    val action = ProfileFragmentDirections.actionNavigationProfileToLogInFragment()
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comeToInfoUserPage()
        if (!viewModel.checkUser()) {
            var user: UserData

            with(viewModel) {
                getUser()

                userLiveData.observe(viewLifecycleOwner) {
                    user = it
                    binding.hello.text = getString(R.string.hello, it.name)
                    binding.discount.text = it.discountPoints
                }
            }
        }
    }

    private fun comeToInfoUserPage() {
        binding.profileInfo.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavigationProfileToInfoUserFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}