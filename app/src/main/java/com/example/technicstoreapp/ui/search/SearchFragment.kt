package com.example.technicstoreapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentSearchBinding
import com.example.technicstoreapp.ui.catalog.CatalogFragmentDirections
import com.example.technicstoreapp.ui.catalog.category_page.CategoryPageAdapter
import com.example.technicstoreapp.ui.catalog.category_page.CategoryPageFragmentDirections
import com.example.technicstoreapp.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(){

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeTechnicLiveData()
        setupCatalogRecyclerView()
    }

    private fun observeTechnicLiveData() {
        viewModel.searchLiveData.observe(viewLifecycleOwner) { technicList ->
            binding.recyclerSearch.adapter?.let { adapter ->
                if (adapter is SearchAdapter) {
                    adapter.setItems(technicList)
                }
            }
        }
    }

    private fun setupCatalogRecyclerView() {
        val catalogAdapter = SearchAdapter(::onItemClick)
        binding.recyclerSearch.apply {
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getSearchResult(arguments?.getString(SEARCH)!!)
    }

    private fun onItemClick(id: Int) {
        val action = CatalogFragmentDirections.actionNavigationCatalogToTechnicPageFragment(id)
        findNavController().navigate(action)
    }

    companion object {
        private const val SEARCH = "SEARCH"

        fun newInstance(searchString: String): SearchFragment {
            val fragment = SearchFragment()
            val args = Bundle()
            args.putString(SEARCH, searchString)
            fragment.arguments = args
            return fragment
        }
    }
}