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

        val itemClick: (String, String, String, String) -> Unit = { name, imageUrl, description, price ->
            val action = HomeFragmentDirections.actionNavigationHomeToTechnicPageFragment(name, imageUrl, description, price)
            findNavController().navigate(action)
        }

        val recyclerPopular = binding.recyclerPopular
        val adapter = PopularAdapter(itemClick)
        recyclerPopular.adapter = adapter
        recyclerPopular.layoutManager = LinearLayoutManager(
            this@HomeFragment.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val recyclerNews = binding.recyclerNews
        val adapterNews = NewsAdapter()
        recyclerNews.adapter = adapterNews
        recyclerNews.layoutManager = LinearLayoutManager(
            this@HomeFragment.context,
            LinearLayoutManager.VERTICAL,
            false
        )

        with(viewModel) {
            getTechnic()

            technicLiveData.observe(viewLifecycleOwner) {
                adapter.setItems(it)
            }

            setUserToken()

            getNews()

            newsLiveData.observe(viewLifecycleOwner) {
                adapterNews.setItems(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}