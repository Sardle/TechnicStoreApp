package com.example.technicstoreapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.allViews
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).selectedItemId = R.id.navigation_profile
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

            with(viewModel) {
                getUser()

                loadingLiveData.observe(viewLifecycleOwner) {
                    checkLoading(it)
                }

                userLiveData.observe(viewLifecycleOwner) {
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

    private fun checkLoading(exists: Boolean) {
        for (view in requireView().allViews) {
            if (view is ProgressBar) {
                view.isVisible = exists
            } else if (view !is ConstraintLayout){
                view.isVisible = !exists
            }
        }
    }
}