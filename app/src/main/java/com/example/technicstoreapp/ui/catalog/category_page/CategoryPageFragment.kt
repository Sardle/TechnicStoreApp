package com.example.technicstoreapp.ui.catalog.category_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.databinding.FragmentCategoryPageBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nameCategory.text = args.category
        binding.backCategory.setOnClickListener {
            requireActivity().onBackPressed()
        }

        setupCatalogRecyclerView()
        observeTechnicLiveData()
    }

    private fun observeTechnicLiveData() {
        viewModel.technicLiveData.observe(viewLifecycleOwner) { technicList ->
            binding.categoriesInfo.adapter?.let { adapter ->
                if (adapter is CategoryPageAdapter) {
                    adapter.setItems(technicList)
                }
            }
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
        val action = CategoryPageFragmentDirections.actionCategoryPageFragmentToTechnicPageFragment(id)
        findNavController().navigate(action)
    }
}