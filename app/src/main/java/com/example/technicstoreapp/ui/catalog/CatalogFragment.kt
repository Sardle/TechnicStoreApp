package com.example.technicstoreapp.ui.catalog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentCatalogBinding
import com.example.technicstoreapp.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CatalogViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
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

        observeTechnicLiveData()
        setupCatalogRecyclerView()

        binding.searchCatalog.setOnClickListener {
            val action = CatalogFragmentDirections.actionNavigationCatalogToSearchFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupCatalogRecyclerView() {
        val catalogAdapter = CatalogAdapter(::onItemClick)

        binding.categories.apply {
            adapter = catalogAdapter
            layoutManager = GridLayoutManager(
                this@CatalogFragment.context,
                2,
                GridLayoutManager.VERTICAL,
                false
            )
        }

        viewModel.getCategories()
    }

    private fun observeTechnicLiveData() {
        observeLoadingLiveData()
        viewModel.categoriesLiveData.observe(viewLifecycleOwner) { technicList ->
            binding.categories.adapter?.let { adapter ->
                if (adapter is CatalogAdapter) {
                    adapter.setItems(technicList)
                }
            }
        }
    }

    private fun observeLoadingLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBarCatalog.isVisible = it
        }
    }

    private fun onItemClick(category: String) {
        val action =
            CatalogFragmentDirections.actionNavigationCatalogToCategoryPageFragment(category)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}