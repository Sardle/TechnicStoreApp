package com.example.technicstoreapp.ui.search

import android.content.Context
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentSearchBinding
import com.example.technicstoreapp.di.app.App
import com.example.technicstoreapp.di.view_model.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: SearchViewModel by viewModels { factory }
    private var _binding: FragmentSearchBinding? = null
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
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView.menu.findItem(R.id.navigation_catalog).let { menu ->
            menu.isChecked = true
        }
        if (binding.search.text.toString() != "") {
            binding.recyclerSearch.isVisible = true
            viewModel.getSearchResult(binding.search.text.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.requestFocus()
        observeLoadingLiveData()
        observeCheckEmptyLiveData()
        observeTechnicLiveData()
        setupCatalogRecyclerView()
        back()
    }

    private fun back() {
        binding.cancel.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
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

    private fun observeLoadingLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBarSearch.isVisible = it
        }
    }

    private fun observeCheckEmptyLiveData() {
        viewModel.checkEmptyLiveData.observe(viewLifecycleOwner) {
            binding.animationViewSearch.isVisible = it
        }
    }

    private fun setupCatalogRecyclerView() {
        val catalogAdapter = SearchAdapter(::onItemClick)
        binding.recyclerSearch.apply {
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    binding.recyclerSearch.isVisible = true
                    viewModel.getSearchResult(binding.search.text.toString())
                } else {
                    binding.progressBarSearch.isVisible = false
                    binding.recyclerSearch.isVisible = false
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.search.addTextChangedListener(textWatcher)
    }

    private fun onItemClick(id: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToTechnicPageFragment(id)
        findNavController().navigate(action)
    }
}