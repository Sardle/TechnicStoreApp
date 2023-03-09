package com.example.technicstoreapp.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.databinding.FragmentCatalogBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeTechnicLiveData()
        setupCatalogRecyclerView()
    }

    private fun setupCatalogRecyclerView() {
        val catalogAdapter = CatalogAdapter(::onItemClick)

        binding.categories.apply {
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getCategories()
    }

    private fun observeTechnicLiveData() {
        viewModel.categoriesLiveData.observe(viewLifecycleOwner) { technicList ->
            binding.categories.adapter?.let { adapter ->
                if (adapter is CatalogAdapter) {
                    adapter.setItems(technicList)
                }
            }
        }
    }

    private fun onItemClick(category: String) {
        val action = CatalogFragmentDirections.actionNavigationCatalogToCategoryPageFragment(category)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}