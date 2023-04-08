package com.example.technicstoreapp.ui.profile.history_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentHistoryOrderBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryOrderFragment : Fragment() {

    private var _binding: FragmentHistoryOrderBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HistoryOrderViewModel>()

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

        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView.menu.findItem(R.id.navigation_profile).let { menu ->
            menu.isChecked = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupHistoryOrderRecyclerView()
        observeHistoryOrderLiveData()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}