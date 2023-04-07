package com.example.technicstoreapp.ui.cart.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentNotAuthenticationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotAuthenticationFragment : Fragment() {

    private var _binding: FragmentNotAuthenticationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotAuthenticationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.comeToAuth.setOnClickListener {
            val navController =
                requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
            navController.popBackStack(R.id.navigation_cart, false)

            val bottomNavigationView =
                requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
            bottomNavigationView.menu.findItem(R.id.navigation_profile).let { menu ->
                menu.isChecked = true
            }
            bottomNavigationView.selectedItemId = R.id.navigation_profile
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}