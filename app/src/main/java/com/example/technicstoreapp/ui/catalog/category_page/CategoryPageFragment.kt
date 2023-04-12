package com.example.technicstoreapp.ui.catalog.category_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentCategoryPageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryPageFragment : Fragment() {

    private var _binding: FragmentCategoryPageBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CategoryPageViewModel>()
    private val args: CategoryPageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView.menu.findItem(R.id.navigation_catalog).let { menu ->
            menu.isChecked = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryName.text = args.category
        binding.backCategory.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        setupCatalogRecyclerView()
        observeTechnicLiveData()
    }

    private fun observeTechnicLiveData() {
        observeLoadingLiveData()
        viewModel.technicLiveData.observe(viewLifecycleOwner) { technicList ->
            binding.categoriesInfo.adapter?.let { adapter ->
                if (adapter is CategoryPageAdapter) {
                    adapter.setItems(technicList)
                }
            }
        }
    }

    private fun observeLoadingLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBarCategoryPage.isVisible = it
        }
    }

    private fun setupCatalogRecyclerView() {
        val catalogAdapter = CategoryPageAdapter(::onItemClick)

        binding.categoriesInfo.apply {
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getTechnic(args.category)
    }

    private fun onItemClick(id: Int) {
        val action =
            CategoryPageFragmentDirections.actionCategoryPageFragmentToTechnicPageFragment(id)
        findNavController().navigate(action)
    }
}