package com.example.technicstoreapp.ui.profile.history_order

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentHistoryOrderBinding
import com.example.technicstoreapp.di.app.App
import com.example.technicstoreapp.di.view_model.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class HistoryOrderFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: HistoryOrderViewModel by viewModels { factory }
    private var _binding: FragmentHistoryOrderBinding? = null
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
        _binding = FragmentHistoryOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView.menu.findItem(R.id.navigation_profile).let { menu ->
            menu.isChecked = true
        }
        checkHistoryIsEmpty()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLoadingLiveData()
        checkHistoryIsEmpty()
        setupHistoryOrderRecyclerView()
        observeHistoryOrderLiveData()
        comeToCatalog()
        back()
    }

    private fun observeLoadingLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.recyclerHistory.isVisible = !it
            binding.progressBarHistory.isVisible = it
        }
    }

    private fun checkHistoryIsEmpty() {
        with(viewModel) {
            checkHistoryListIsEmpty()

            checkLiveData.observe(viewLifecycleOwner) {
                binding.historyGroup.isVisible = it
            }
        }
    }

    private fun comeToCatalog() {
        binding.comeToCatalogFromHistory.setOnClickListener {
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

    private fun setupHistoryOrderRecyclerView() {
        val catalogAdapter = HistoryOrderAdapter()

        binding.recyclerHistory.apply {
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(
                this@HistoryOrderFragment.context,
                GridLayoutManager.VERTICAL,
                false
            )
        }

        viewModel.getHistoryOrderList()
    }

    private fun observeHistoryOrderLiveData() {
        viewModel.historyOrderLiveData.observe(viewLifecycleOwner) { historyList ->
            binding.recyclerHistory.adapter?.let { adapter ->
                if (adapter is HistoryOrderAdapter) {
                    adapter.setItems(historyList)
                }
            }
        }
    }

    private fun back() {
        binding.backHistory.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}