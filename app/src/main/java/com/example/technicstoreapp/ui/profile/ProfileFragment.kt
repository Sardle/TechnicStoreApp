package com.example.technicstoreapp.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentProfileBinding
import com.example.technicstoreapp.App
import com.example.technicstoreapp.di.view_model.ViewModelFactory
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: ProfileViewModel by viewModels { factory }
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).appComponent.inject(this)
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

        checkNetworkConnection()
        retry()
        comeToInfoUserPage()
    }

    private fun retry() {
        binding.retryProfile.setOnClickListener {
            viewModel.checkNetworkConnection()
        }
    }

    private fun checkNetworkConnection() {
        viewModel.checkNetworkConnection()
        viewModel.checkNetworkLiveData.observe(viewLifecycleOwner) { checkNetwork ->
            if (checkNetwork) {
                binding.noInternetGroup.isVisible = false
                setupPage()
            } else {
                with(binding) {
                    noInternetGroup.isVisible = true
                    profileGroup.isVisible = false
                }
            }
        }
    }

    private fun setupPage() {
        if (!viewModel.checkUser()) {

            comeToHistory()

            comeToFavourite()

            with(viewModel) {
                getUser()

                loadingLiveData.observe(viewLifecycleOwner) { show ->
                    binding.profileGroup.isVisible = !show
                    binding.progressBarProfile.isVisible = show
                }

                userLiveData.observe(viewLifecycleOwner) { user ->
                    binding.hello.text = getString(R.string.hello, user.name)
                    binding.discount.text = user.discountPoints.toString()
                }
            }
        } else {
            val action = ProfileFragmentDirections.actionNavigationProfileToLogInFragment()
            findNavController().navigate(action)
        }
    }

    private fun comeToFavourite() {
        binding.favourites.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavigationProfileToFavouriteFragment()
            findNavController().navigate(action)
        }
    }

    private fun comeToHistory() {
        binding.orderHistory.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavigationProfileToHistoryOrderFragment()
            findNavController().navigate(action)
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