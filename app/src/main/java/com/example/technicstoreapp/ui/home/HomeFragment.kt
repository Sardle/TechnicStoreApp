package com.example.technicstoreapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentHomeBinding
import com.example.technicstoreapp.di.app.App
import com.example.technicstoreapp.di.view_model.ViewModelFactory
import com.example.technicstoreapp.ui.home.news_recycler.NewsAdapter
import com.example.technicstoreapp.ui.home.popular.PopularAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: HomeViewModel by viewModels { factory }
    private var _binding: FragmentHomeBinding? = null
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBadgeCart()
        setupPopularRecyclerView()
        setupNewsRecyclerView()
        observeTechnicLiveData()
        observeNewsLiveData()
        observeLoadingLiveData()
        checkNetworkConnection()
        retry()
    }

    private fun retry() {
        binding.retryHome.setOnClickListener {
            viewModel.checkNetworkConnection()
        }
    }

    private fun checkNetworkConnection() {
        viewModel.checkNetworkConnection()
        viewModel.checkNetworkLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.homeAll.isVisible = true
                binding.noInternetGroup.isVisible = false
                binding.searchLayout.isVisible = true
                setupViewModel()
            } else {
                binding.homeAll.isVisible = false
                binding.noInternetGroup.isVisible = true
                binding.searchLayout.isVisible = false
            }
        }
    }

    private fun observeLoadingLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.homeAll.isVisible = !it
            binding.progressBarHome.isVisible = it
        }
    }

    private fun setupViewModel() {
        viewModel.setUserToken()
        viewModel.setupBadgeCart()
        viewModel.getNews()
        viewModel.getTechnic()
    }

    private fun setupBadgeCart() {
        viewModel.countLiveData.observe(viewLifecycleOwner) {
            val bottomNavigationView =
                requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
            val badge = bottomNavigationView.getOrCreateBadge(R.id.navigation_cart)
            badge.number = it
        }
    }

    private fun setupPopularRecyclerView() {
        val popularAdapter = PopularAdapter(::onItemClick)

        binding.recyclerPopular.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupNewsRecyclerView() {
        val newsAdapter = NewsAdapter()

        binding.recyclerNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeTechnicLiveData() {
        viewModel.technicLiveData.observe(viewLifecycleOwner) { technicList ->
            binding.recyclerPopular.adapter?.let { adapter ->
                if (adapter is PopularAdapter) {
                    adapter.setItems(technicList)
                }
            }
        }
    }

    private fun observeNewsLiveData() {
        viewModel.newsLiveData.observe(viewLifecycleOwner) { newsList ->
            binding.recyclerNews.adapter?.let { adapter ->
                if (adapter is NewsAdapter) {
                    adapter.setItems(newsList)
                }
            }
        }
    }

    private fun onItemClick(id: Int) {
        val action = HomeFragmentDirections.actionNavigationHomeToTechnicPageFragment(id)
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}