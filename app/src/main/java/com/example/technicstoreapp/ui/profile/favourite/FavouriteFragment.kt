package com.example.technicstoreapp.ui.profile.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentFavouriteBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FavouriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView.menu.findItem(R.id.navigation_profile).let { menu ->
            menu.isChecked = true
        }
        checkFavouriteIsEmpty()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkFavouriteIsEmpty()
        observeCartTechnicLiveData()
        setupCatalogRecyclerView()
        observeLoadingLiveData()
        comeToCatalog()
    }

    private fun checkFavouriteIsEmpty() {
        with(viewModel) {
            checkFavouriteListIsEmpty()

            checkLiveData.observe(viewLifecycleOwner) {
                binding.favouriteGroup.isVisible = it
            }
        }
    }

    private fun comeToCatalog() {
        binding.comeToCatalogFromFav.setOnClickListener {
            val navController =
                requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
            navController.popBackStack(R.id.navigation_profile, false)

            val bottomNavigationView =
                requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
            bottomNavigationView.menu.findItem(R.id.navigation_catalog).let { menu ->
                menu.isChecked = true
            }
            bottomNavigationView.selectedItemId = R.id.navigation_catalog
        }
    }

    private fun observeLoadingLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.recyclerFavourite.isVisible = !it
            binding.progressBarFavourite.isVisible = it
        }
    }

    private fun observeCartTechnicLiveData() {
        viewModel.favouriteTechnicLiveData.observe(viewLifecycleOwner) { technicList ->
            binding.recyclerFavourite.adapter?.let { adapter ->
                if (adapter is FavouriteAdapter) {
                    adapter.setItems(technicList)
                }
            }
        }
    }

    private fun setupCatalogRecyclerView() {
        val catalogAdapter = FavouriteAdapter(
            ::onItemClick,
            ::onDeleteClick
        )

        binding.recyclerFavourite.apply {
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getFavouriteTechnic()
    }

    private fun onItemClick(id: Int) {
        val action = FavouriteFragmentDirections.actionFavouriteFragmentToTechnicPageFragment(id)
        findNavController().navigate(action)
    }

    private fun onDeleteClick(id: Int, rowView: View) {
        val anim = AnimationUtils.loadAnimation(rowView.context, R.anim.rv_remove_fav)
        rowView.startAnimation(anim)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                viewModel.removeFromFavourite(id)
            }

            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}