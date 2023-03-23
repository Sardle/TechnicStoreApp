package com.example.technicstoreapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.databinding.FragmentHomeBinding
import com.example.technicstoreapp.ui.home.news_recycler.NewsAdapter
import com.example.technicstoreapp.ui.home.popular.PopularAdapter
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()

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


        viewModel.setUserToken()
        setupPopularRecyclerView()
        setupNewsRecyclerView()
        observeTechnicLiveData()
        observeNewsLiveData()
    }

    private fun setupPopularRecyclerView() {
        val popularAdapter = PopularAdapter(::onItemClick)

        binding.recyclerPopular.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.getTechnic()
    }

    private fun setupNewsRecyclerView() {
        val newsAdapter = NewsAdapter()

        binding.recyclerNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getNews()
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